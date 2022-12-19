package ru.kataproject.p_sm_airlines_1.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kataproject.p_sm_airlines_1.entity.Aircraft;
import ru.kataproject.p_sm_airlines_1.entity.Dto.AircraftDto;
import ru.kataproject.p_sm_airlines_1.repository.AircraftRepository;
import ru.kataproject.p_sm_airlines_1.service.AircraftService;
import ru.kataproject.p_sm_airlines_1.util.mapper.mapStruct.AircraftMapper;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Сервис для работы с бизнеслогикой Aircraft
 */

@Service
@RequiredArgsConstructor
public class AircraftServiceImpl implements AircraftService {
    private final AircraftRepository aircraftRepository;
    private final AircraftMapper aircraftMapper;

    @Transactional
    @Override
    public List<AircraftDto> getAllAircrafts() {
        return aircraftRepository.findAll()
                .stream()
                //.map(AircraftMapper::mapped)
                .map(aircraftMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Aircraft saveAircraft(Aircraft aircraft) {
        return aircraftRepository.save(aircraft);
    }

    @Transactional
    @Override
    public void updateAircraft(Aircraft aircraft) {
        aircraftRepository.save(aircraft);
    }

    @Override
    public Aircraft getAircraftById(Long id) {
        Optional<Aircraft> aircraft = aircraftRepository.findById(id);
        return aircraft.orElseThrow();
    }

    @Override
    public void delete(Aircraft aircraft) {
        aircraftRepository.delete(aircraft);
    }

}
