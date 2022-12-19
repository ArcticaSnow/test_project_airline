package ru.kataproject.p_sm_airlines_1.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * Interface TelegramBotService.
 * Declares TelegramBot Service API.
 *
 * @author Ekaterina Kuchmistova (katy.shamina@yandex.ru)
 * @since 14.12.2022
 */
public interface TelegramBotService {
    /**
     * Method welcomes the subscriber.
     */
    void startCommandReceived(Long chatId, String name);

    /**
     * Method registers subscriber.
     */
    void registerSubscriber(Message message);

    /**
     * Method gets subscribers data.
     */
    void myData(Message message, Long chatId);

    /**
     * Method suggests deleting data.
     */
    void deleteData(Long chatId);

    /**
     * Method deletes subscriber.
     */
    void deleteSubscriber(Long chatId);

    /**
     * Method sends a message and shows the keyboard.
     */
    void sendMessage(Long chatId, String textToSend);

    /**
     * Method sends a message and doesn't show the keyboard.
     */
    void prepareAndSendMessage(Long chatId, String textToSend);

    /**
     * Method sends the message text.
     */
    void executeSendMessageText(SendMessage sendMessage);

    /**
     * Method sends edited message text.
     */
    void executeEditMessageText(String text, Long chatId, Integer messageId);

    /**
     * Method sends the newsletter automatically.
     */
    void sendMailingAutomatically();

    /**
     * Method sends the newsletter manually.
     */
    void sendMailingManually(String messageText);

}
