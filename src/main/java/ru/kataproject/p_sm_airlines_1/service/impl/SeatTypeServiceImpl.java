package ru.kataproject.p_sm_airlines_1.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kataproject.p_sm_airlines_1.entity.Seat;
import ru.kataproject.p_sm_airlines_1.entity.SeatType;
import ru.kataproject.p_sm_airlines_1.repository.SeatTypeRepository;
import ru.kataproject.p_sm_airlines_1.service.SeatService;
import ru.kataproject.p_sm_airlines_1.service.SeatTypeService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SeatTypeServiceImpl implements SeatTypeService {

    private final SeatTypeRepository seatTypeRepository;
    private final SeatService seatService;

    @Override
    public List<SeatType> getAllSeatTypes() {
        return seatTypeRepository.findAll();
    }

    @Override
    public SeatType getSeatTypeById(Long id) {
        return seatTypeRepository.getById(id);
    }

    @Override
    public void saveSeatType(SeatType seatType) {
        seatTypeRepository.save(seatType);
    }

    @Override
    public void updateSeatType(SeatType seatType) {
        seatTypeRepository.save(seatType);
    }

    @Override
    public void deleteSeatType(SeatType seatType) {
        seatTypeRepository.delete(seatType);
    }

    @Override
    public List<SeatType> getAllSeatTypesInAircraft(Long aircraftId) {
        return seatService.getSeatsByAircraftId(aircraftId)
                .stream()
                .map(Seat::getSeatType)
                .distinct()
                .collect(Collectors.toList());
    }
}
