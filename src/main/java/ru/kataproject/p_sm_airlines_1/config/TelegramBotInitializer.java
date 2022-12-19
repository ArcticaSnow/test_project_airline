package ru.kataproject.p_sm_airlines_1.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.kataproject.p_sm_airlines_1.service.impl.TelegramBotServiceImpl;

@Slf4j
@Component
@AllArgsConstructor
public class TelegramBotInitializer {

    TelegramBotServiceImpl bot;

    @EventListener({ContextRefreshedEvent.class})
    public void init() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);

        try {
            telegramBotsApi.registerBot(bot);
        } catch (TelegramApiException exception) {
            log.error("ERROR_TEXT: " + exception.getMessage());
        }
    }

}