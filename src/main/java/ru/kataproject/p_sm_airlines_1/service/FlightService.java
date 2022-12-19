package ru.kataproject.p_sm_airlines_1.service;

import ru.kataproject.p_sm_airlines_1.entity.Flight;

/**
 * Сервис для работы с бизнеслогикой рейса
 *
 * @author Toboe512
 */
public interface FlightService {

    void saveFlight(Flight flight);

    void updateFlight(Flight flight);

    Flight getFlightById(Long id);

    void delete(Flight flight);
}
