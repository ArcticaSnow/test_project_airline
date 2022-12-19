package ru.kataproject.p_sm_airlines_1.service;

import ru.kataproject.p_sm_airlines_1.entity.Aircraft;
import ru.kataproject.p_sm_airlines_1.entity.Dto.AircraftDto;

import java.util.List;

/**
 * Сервис для работы с бизнеслогикой Aircraft
 */

public interface AircraftService {
    List<AircraftDto> getAllAircrafts();

    Aircraft getAircraftById(Long id);

    void updateAircraft(Aircraft aircraft);

    Aircraft saveAircraft(Aircraft aircraft);

    void delete(Aircraft aircraft);
}
