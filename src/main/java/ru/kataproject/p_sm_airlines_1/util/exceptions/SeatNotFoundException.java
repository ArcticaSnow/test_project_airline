package ru.kataproject.p_sm_airlines_1.util.exceptions;

/**
 * Implements SeatNotFound Exception.
 *
 * @author Romanov Leonid (romanovsparta@ya.ru)
 * @since 14.10.2022
 */
public class SeatNotFoundException extends AbstractResourceNotFoundException {
    @Override
    protected String getResourceAlias() {
        return "Seat";
    }

}
