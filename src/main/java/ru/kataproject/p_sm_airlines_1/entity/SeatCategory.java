package ru.kataproject.p_sm_airlines_1.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Enumeration of ticket categories - "Economy", "Business", "First".
 *
 * @author Romanov Leonid (romanovsparta@ya.ru)
 * @since 07.10.2022
 */
@Getter
@RequiredArgsConstructor
public enum SeatCategory {
    FIRST("First"),
    BUSINESS("Business"),
    ECONOMY("Economy");
    private final String value;
}