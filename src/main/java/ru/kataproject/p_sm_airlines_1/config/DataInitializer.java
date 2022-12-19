package ru.kataproject.p_sm_airlines_1.config;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * В этом классе инициализируются тестовые данные для базы.
 * Эти данные будут каждый раз создаваться заново при поднятии SessionFactory и удаляться из БД при её остановке.
 * Инжектьте и используйте здесь соответствующие сервисы ваших сущностей."
 * После имплементации LiquiBase класс будет удален
 */
@Component
public class DataInitializer {

    @PostConstruct
    public void init() {
        System.out.println("DataInitializer сработал!");
    }
}
