package ru.kataproject.p_sm_airlines_1.service;

import ru.kataproject.p_sm_airlines_1.entity.Seat;

import java.util.List;

/**
 * Declares Seat Service API.
 *
 * @author Romanov Leonid (romanovsparta@ya.ru)
 * @since 07.10.2022
 */
public interface SeatService {

    /**
     * This method returns all Seats.
     *
     * @return List<Seat>
     */
    List<Seat> getAllSeats();

    /**
     * This method returns a seat by id.
     *
     * @param id Long
     * @return Seat
     */
    Seat getSeatById(Long id);

    /**
     * This method creates a new seat.
     *
     * @param seat Seat
     */
    void saveSeat(Seat seat);

    /**
     * This method updates the seat.
     *
     * @param seat Seat
     */
    void updateSeat(Seat seat);

    /**
     * This method deletes the seat.
     *
     * @param seat Seat
     */
    void deleteSeat(Seat seat);

    /**
     * This method returns all seats on the aircraft by Aircraft id.
     *
     * @param aircraftId Long
     * @return List<Seat>
     */
    List<Seat> getSeatsByAircraftId(Long aircraftId);

    /**
     * This method returns all seats of a certain category on a aircraft by Aircraft id & Seat Type.
     *
     * @param aircraftId Long
     * @param seatType   Seat Type
     * @return List<Seat>
     */
    List<Seat> getSeatByAircraftAndSeatTypeId(Long aircraftId, Long seatTypeId);
}