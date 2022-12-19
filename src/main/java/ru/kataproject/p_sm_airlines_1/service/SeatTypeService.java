package ru.kataproject.p_sm_airlines_1.service;

import ru.kataproject.p_sm_airlines_1.entity.SeatType;

import java.util.List;

public interface SeatTypeService {

    /**
     * This method returns all SeatTypes.
     *
     * @return List<SeatType>
     */
    List<SeatType> getAllSeatTypes();

    /**
     * This method returns a seatType by id.
     *
     * @param id Long
     * @return SeatType
     */
    SeatType getSeatTypeById(Long id);

    /**
     * This method creates a new seatType.
     *
     * @param seatType SeatType
     */
    void saveSeatType(SeatType seatType);

    /**
     * This method updates the seatType.
     *
     * @param seatType SeatType
     */
    void updateSeatType(SeatType seatType);

    /**
     * This method deletes the seatType.
     *
     * @param seatType SeatType
     */
    void deleteSeatType(SeatType seatType);

    /**
     * This method returns all SeatTypes in Aircraft.
     *
     * @return SeatType
     */
    List<SeatType> getAllSeatTypesInAircraft(Long aircraftId);
}
