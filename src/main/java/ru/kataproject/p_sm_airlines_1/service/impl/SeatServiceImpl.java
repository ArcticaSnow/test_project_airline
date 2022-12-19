package ru.kataproject.p_sm_airlines_1.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kataproject.p_sm_airlines_1.entity.Destination;
import ru.kataproject.p_sm_airlines_1.entity.Dto.DestinationDTO;
import ru.kataproject.p_sm_airlines_1.entity.Seat;
import ru.kataproject.p_sm_airlines_1.repository.DestinationRepository;
import ru.kataproject.p_sm_airlines_1.repository.SeatRepository;
import ru.kataproject.p_sm_airlines_1.service.DestinationService;
import ru.kataproject.p_sm_airlines_1.service.SeatService;
import ru.kataproject.p_sm_airlines_1.util.exceptions.SeatNotFoundException;
import ru.kataproject.p_sm_airlines_1.util.mapper.mapStruct.DestinationMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implements SeatService interface.
 * Changed by:
 *
 * @author Alexey Sen (alexey.sen@gmail.com)
 * @since 01.11.2022
 */
@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {
    private final SeatRepository seatRepository;


    @Override
    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }

    @Override
    public Seat getSeatById(Long id) {
        Optional<Seat> seat = seatRepository.findById(id);
        return seat.orElseThrow(SeatNotFoundException::new);
    }

    @Override
    @Transactional
    public void saveSeat(Seat seat) {
        seatRepository.save(seat);
    }

    @Override
    @Transactional
    public void updateSeat(Seat seat) {
        seatRepository.save(seat);
    }

    @Override
    @Transactional
    public void deleteSeat(Seat seat) {
        seatRepository.delete(seat);
    }

    @Override
    public List<Seat> getSeatsByAircraftId(Long aircraftId) {
        return seatRepository.getSeatsByAircraft_Id(aircraftId);
    }

    @Override
    public List<Seat> getSeatByAircraftAndSeatTypeId(Long aircraftId, Long seatTypeId) {
        return seatRepository.getSeatsByAircraft_IdAndSeatType_Id(aircraftId, seatTypeId);
    }
}
