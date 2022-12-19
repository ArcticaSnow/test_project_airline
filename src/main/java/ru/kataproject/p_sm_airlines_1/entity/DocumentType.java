package ru.kataproject.p_sm_airlines_1.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Enum DocumentType.
 * Holds document types.
 *
 * @author Mishin Yura (mishin.inbox@gmail.com)
 * @since 13.10.2022
 */
@Getter
@RequiredArgsConstructor
public enum DocumentType {
    /**
     * National passport.
     */
    NATIONAL_PASSPORT("National passport"),

    /**
     * International passport.
     */
    INTERNATIONAL_PASSPORT("International passport");

    /**
     * Enum value.
     */
    private final String value;
}
