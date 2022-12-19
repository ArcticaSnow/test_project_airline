package ru.kataproject.p_sm_airlines_1.controller.impl;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.kataproject.p_sm_airlines_1.controller.SeatController;
import ru.kataproject.p_sm_airlines_1.entity.Dto.SeatDto;
import ru.kataproject.p_sm_airlines_1.entity.Seat;
import ru.kataproject.p_sm_airlines_1.service.SeatService;
import ru.kataproject.p_sm_airlines_1.util.mapper.mapStruct.SeatMapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller for processing requests for add, change and get seat data.
 * Implements interface SeatController
 *
 * @author Romanov Leonid (romanovsparta@ya.ru)
 * @since 12.10.2022
 */
@Log4j2
@RestController
@SecurityRequirement(name = "swagger_auth")
@RequiredArgsConstructor
@SecurityRequirement(name = "swagger_auth")
public class SeatControllerImpl implements SeatController {
    private final SeatService seatService;
    private final SeatMapper seatMapper;

    @Override
    @ResponseStatus(HttpStatus.OK)
    public List<SeatDto> getAllSeats() {
        log.info("execute getAllSeats method");
        return seatService.getAllSeats()
                .stream()
                .map(seatMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    public SeatDto getSeatById(Long id) {
        log.info("execute getSeatById method");
        return seatMapper.toDto(seatService.getSeatById(id));
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    public void createSeat(SeatDto seatDTO) {
        log.info("execute createSeat method");
        seatService.saveSeat(seatMapper.toModel(seatDTO));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    public void updateSeat(SeatDto seatDTO) {
        log.info("execute updateSeat method");
        seatService.updateSeat(seatMapper.toModel(seatDTO));
    }

    @Override
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSeat(Long id) {
        log.info("execute deleteSeat method");
        Seat seat = seatService.getSeatById(id);
        seatService.deleteSeat(seat);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    public List<SeatDto> getAllSeatsInAircraft(Long aircraftId) {
        System.out.println(aircraftId);
        log.info("execute getAllSeatsInAircraft method");
        return seatService.getSeatsByAircraftId(aircraftId).stream().map(seatMapper::toDto).collect(Collectors.toList());
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    public List<SeatDto> getSeatByAircraftAndSeatTypeId(Long aircraftId, Long seatTypeId) {
        log.info("execute getSeatsInAircraftBySeatType method");
        return seatService.getSeatByAircraftAndSeatTypeId(aircraftId, seatTypeId).stream().map(seatMapper::toDto).collect(Collectors.toList());
    }

}