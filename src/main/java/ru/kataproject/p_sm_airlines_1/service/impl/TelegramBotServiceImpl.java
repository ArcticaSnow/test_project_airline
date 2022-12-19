package ru.kataproject.p_sm_airlines_1.service.impl;

import com.vdurmont.emoji.EmojiParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.kataproject.p_sm_airlines_1.config.TelegramBotConfig;
import ru.kataproject.p_sm_airlines_1.entity.TelegramMailings;
import ru.kataproject.p_sm_airlines_1.entity.TelegramSubscriber;
import ru.kataproject.p_sm_airlines_1.repository.TelegramMailingsRepository;
import ru.kataproject.p_sm_airlines_1.repository.TelegramSubscriberRepository;
import ru.kataproject.p_sm_airlines_1.service.TelegramBotService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Class TelegramBotServiceImpl.
 * Implements TelegramBotService interface and extends TelegramLongPollingBot class.
 *
 * @author Ekaterina Kuchmistova (katy.shamina@yandex.ru)
 * @since 14.12.2022
 */
@Slf4j
@Service
//@AllArgsConstructor
public class TelegramBotServiceImpl extends TelegramLongPollingBot implements TelegramBotService {

    private final TelegramSubscriberRepository subscriberRepository;

    private final TelegramBotConfig telegramBotConfig;

    private final TelegramMailingsRepository mailingsRepository;

    private final String YES_BUTTON = "YES_BUTTON";
    private final String NO_BUTTON = "NO_BUTTON";

    static final String HELP_TEXT = "This bot sends emails to UX Air passengers. Welcome aboard!.\n\n" +
            "You can execute commands from the main menu on the left or by typing a command:\n\n" +
            "Type /start to see a welcome message and register\n\n" +
            "Type /mydata to see data stored about yourself\n\n" +
            "Type /deletedata delete your data\n\n" +
            "Type /help to see this message again\n\n";

    public TelegramBotServiceImpl(TelegramBotConfig telegramBotConfig, TelegramSubscriberRepository subscriberRepository, TelegramMailingsRepository mailingsRepository) {
        this.telegramBotConfig = telegramBotConfig;
        this.subscriberRepository = subscriberRepository;
        this.mailingsRepository = mailingsRepository;
        List<BotCommand> listOfCommands = new ArrayList<>();

        listOfCommands.add(new BotCommand("/start", "get a welcome message"));
        listOfCommands.add(new BotCommand("/mydata", "get your data stored"));
        listOfCommands.add(new BotCommand("/deletedata", "delete my data"));
        listOfCommands.add(new BotCommand("/help", "info how to use this bot"));

        try {
            this.execute(new SetMyCommands(listOfCommands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException exception) {
            log.error("ERROR_TEXT setting bot's command list: " + exception.getMessage());
        }
    }

    @Override
    public String getBotUsername() {
        return telegramBotConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return telegramBotConfig.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) { // update содержит сообщение, которое пользователь посылает боту

        if (update.hasMessage() && update.getMessage().hasText()) { // если сообщение есть и оно содержит текст
            Message message = update.getMessage();
            String messageText = message.getText();
            long chatId = message.getChatId(); // получаем id, который идентифицирует пользователя

            if (messageText.contains("/send") && telegramBotConfig.getOwnerId() == chatId) { // команда для ручной отправки рассылки
                sendMailingManually(messageText);
            } else {
                switch (messageText) { // остальные команды
                    case "/start":
                        registerSubscriber(message);
                        startCommandReceived(chatId, message.getChat().getFirstName());
                        break;
                    case "/help":
                        prepareAndSendMessage(chatId, HELP_TEXT);
                        break;
                    case "/mydata":
                        myData(message, chatId);
                        break;
                    case "/deletedata":
                        deleteData(chatId);
                        break;
                    default:
                        prepareAndSendMessage(chatId, "Sorry, command was not recognized");
                }
            }
        } else if (update.hasCallbackQuery()) { // если пользователь нажал одну из предложенных кнопок на клавиатуре под сообщением с вопросом об удалении
            String callbackData = update.getCallbackQuery().getData();
            Integer messageId = update.getCallbackQuery().getMessage().getMessageId();
            long chatId = update.getCallbackQuery().getMessage().getChatId();

            if (callbackData.equals(YES_BUTTON)) {
                String text = "Your data has been deleted";
                executeEditMessageText(text, chatId, messageId);
                deleteSubscriber(chatId);
            } else if (callbackData.equals(NO_BUTTON)) {
                String text = "You pressed NO button";
                executeEditMessageText(text, chatId, messageId);
            }
        }
    }

    public void startCommandReceived(Long chatId, String name) { // выводим приветственное сообщение
        String answer = EmojiParser.parseToUnicode("Hi, " + name + ", nice to meet you!" + " :blush:");
        log.info("Replied to user " + name);

        sendMessage(chatId, answer);
    }

    public void registerSubscriber(Message message) { // регистрируем подписчика и заносим инфу в бд
        if (subscriberRepository.findById(message.getChatId()).isEmpty()) {
            var chatId = message.getChatId();
            var chat = message.getChat();

            TelegramSubscriber subscriber = new TelegramSubscriber();
            subscriber.setChatId(chatId);
            subscriber.setFirstName(chat.getFirstName());
            subscriber.setLastName(chat.getLastName());
            subscriber.setUsername(chat.getUserName());
            subscriber.setRegisteredAt(new Timestamp(System.currentTimeMillis()));

            subscriberRepository.save(subscriber);
            log.info("Subscriber saved: " + subscriber);
        }
    }

    public void myData(Message message, Long chatId) { // показываем данные подписчика
        String username = message.getChat().getUserName();
        TelegramSubscriber subscriber = subscriberRepository.findByUsername(username);

        if (subscriber == null) {
            prepareAndSendMessage(chatId, "You are not registred");
        } else {
            prepareAndSendMessage(chatId, subscriber.toString());
        }
    }

    public void deleteData(Long chatId) { // предлагаем подписчику удалить свои данные
        SendMessage sendMessage = new SendMessage();

        sendMessage.setChatId(chatId);
        sendMessage.setText("Do you really want to delete data?");

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup(); // разметка для клавиатуры под сообщением

        List<List<InlineKeyboardButton>> buttonRows = new ArrayList<>(); // список списков с кнопками
        List<InlineKeyboardButton> buttonList = new ArrayList<>(); // список с кнопками одного ряда

        var yesButton = new InlineKeyboardButton(); // кнопки
        var noButton = new InlineKeyboardButton();

        yesButton.setText("Yes");
        yesButton.setCallbackData(YES_BUTTON); // определяет, что именно эта кнопка была нажата

        noButton.setText("No");
        noButton.setCallbackData(NO_BUTTON);

        buttonList.add(yesButton);
        buttonList.add(noButton);

        buttonRows.add(buttonList);
        keyboardMarkup.setKeyboard(buttonRows);
        sendMessage.setReplyMarkup(keyboardMarkup);

        executeSendMessageText(sendMessage);
    }

    public void deleteSubscriber(Long chatId) { // удаляем подписчика из бд
        subscriberRepository.deleteById(chatId);
    }

    public void sendMessage(Long chatId, String textToSend) { // отправляем сообщение и показываем клавиатуру с командами
        SendMessage sendMessage = new SendMessage();

        sendMessage.setChatId(chatId);
        sendMessage.setText(textToSend);

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup(); // разметка для клавиатуры

        List<KeyboardRow> keyboardRowList = new ArrayList<>(); // список рядов клавиатуры

        KeyboardRow row = new KeyboardRow(); // ряд

        row.add("/start");
        row.add("/mydata");

        keyboardRowList.add(row);

        KeyboardRow row2 = new KeyboardRow();

        row2.add("/deletedata");
        row2.add("/help");

        keyboardRowList.add(row2);

        keyboardMarkup.setKeyboard(keyboardRowList); // привязываем список к разметке

        sendMessage.setReplyMarkup(keyboardMarkup); // привязываем клавиатуру к ответному сообщению

        executeSendMessageText(sendMessage);
    }

    public void prepareAndSendMessage(Long chatId, String textToSend) { // отправляем сообщение и НЕ показываем клавиатуру
        SendMessage sendMessage = new SendMessage();

        sendMessage.setChatId(chatId);
        sendMessage.setText(textToSend);
        executeSendMessageText(sendMessage);
    }

    public void executeSendMessageText(SendMessage sendMessage) { // отправляем сообщение
        try {
            execute(sendMessage);
        } catch (TelegramApiException exception) {
            log.error("ERROR_TEXT: " + exception.getMessage());
        }
    }

    public void executeEditMessageText(String text, Long chatId, Integer messageId) { // отправляем отредактированное сообщение
        EditMessageText editMessage = new EditMessageText();
        editMessage.setChatId(chatId);
        editMessage.setText(text);
        editMessage.setMessageId(messageId);

        try {
            execute(editMessage);
        } catch (TelegramApiException exception) {
            log.error("ERROR_TEXT: " + exception.getMessage());
        }
    }

    @Scheduled(cron = "0 * * * * * ")
    public void sendMailingAutomatically() { // отправляем автоматическую рассылку раз в минуту

        var mailings = mailingsRepository.findAll();
        var subscribers = subscriberRepository.findAll();

        if (mailings.isEmpty()) {
            TelegramMailings mailingTest = new TelegramMailings();
            mailingTest.setTextMailing("Attention! This is a test mailing");
            mailingTest.setComment("Test mailing");
            mailingsRepository.save(mailingTest); // в тесты?
        }

        for (TelegramMailings mailing : mailings) {
            for (TelegramSubscriber subscriber : subscribers) {
                prepareAndSendMessage(subscriber.getChatId(), mailing.getTextMailing());
            }
        }
    }

    public void sendMailingManually(String messageText) { // отправляем рассылку вручную
        var textToSend = EmojiParser.parseToUnicode(messageText.substring(messageText.indexOf(" ")));
        var subscribers = subscriberRepository.findAll();
        for (TelegramSubscriber subscriber : subscribers) {
            prepareAndSendMessage(subscriber.getChatId(), textToSend);
        }
    }
}
