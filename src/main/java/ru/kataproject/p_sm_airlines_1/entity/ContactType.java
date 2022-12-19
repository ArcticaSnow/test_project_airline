package ru.kataproject.p_sm_airlines_1.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ContactType {
    /**
     * Email.
     */
    EMAIL("email"),

    /**
     * Phone.
     */
    PHONE("phone"),

    /**
     * Telegram.
     */
    TELEGRAM("telegram"),

    /**
     * Whatsapp.
     */
    WHATSAPP("whatsapp");

    /**
     * Enum value.
     */
    private final String value;
}
