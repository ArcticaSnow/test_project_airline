package ru.kataproject.p_sm_airlines_1.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.kataproject.p_sm_airlines_1.controller.FlightController;
import ru.kataproject.p_sm_airlines_1.entity.Dto.FlightDto;
import ru.kataproject.p_sm_airlines_1.service.FlightService;
import ru.kataproject.p_sm_airlines_1.util.mapper.mapStruct.FlightMapper;

/**
 * Контроллер для обработки запросов по добавлению, изменению и получению данных рейса
 * <p>
 * (C)Toboe512
 * @author Alexey Sen (alexey.sen@gmail.com)
 */
@Log4j2
@RestController
@RequiredArgsConstructor
public class FlightControllerImpl implements FlightController {

    private final FlightService flightService;
    private final FlightMapper flightMapper;


    @Override
    @ResponseStatus(HttpStatus.OK)
    public FlightDto getFlightById(Long id) {
        return flightMapper.toDto(flightService.getFlightById(id));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    public void createFlight(FlightDto flight) {
        log.info("execute createFlight method");
        flightService.saveFlight(flightMapper.toModel(flight));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    public void updateFlight(FlightDto flight) {
        log.info("execute updateFlight method");
        flightService.updateFlight(flightMapper.toModel(flight));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    public void deleteFlightById(Long id) {
        log.info("execute deleteFlightById method");
        flightService.delete(flightService.getFlightById(id));
    }

    //TODO после добавления Seat доработать
    @Override
    @ResponseStatus(HttpStatus.OK)
    public String getFreeSeatsOnFlight(FlightDto flight) {
        log.info("execute getFreeSeatsOnFlight method");
        return null;
    }
}
