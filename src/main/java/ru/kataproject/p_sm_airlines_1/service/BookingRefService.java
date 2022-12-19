package ru.kataproject.p_sm_airlines_1.service;

/**
 * Interface BookingRef.
 * Declares BookingRef Service API.
 *
 * @author Alexey Sen (alexey.sen@gmail.com)
 * @since 31.10.2022
 */
public interface BookingRefService {
    /**
     * Return booking reference number.
     * @return String
     */
    String createRefNumber();
}
