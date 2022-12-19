package ru.kataproject.p_sm_airlines_1.controller.impl;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.kataproject.p_sm_airlines_1.controller.SeatTypeController;
import ru.kataproject.p_sm_airlines_1.entity.Dto.SeatTypeDTO;
import ru.kataproject.p_sm_airlines_1.service.SeatTypeService;
import ru.kataproject.p_sm_airlines_1.util.mapper.mapStruct.SeatTypeMapper;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "swagger_auth")
public class SeatTypeControllerImpl implements SeatTypeController {
    private final SeatTypeService seatTypeService;
    private final SeatTypeMapper seatTypeMapper;

    @Override
    @ResponseStatus(HttpStatus.OK)
    public List<SeatTypeDTO> getAllSeatTypes() {
        log.info("executed getAllSeatTypes() method");
        return seatTypeService.getAllSeatTypes()
                .stream()
                .map(seatTypeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    public SeatTypeDTO getSeatTypeById(Long id) {
        log.info("executed getSeatTypeById() method");
        return seatTypeMapper.toDTO(seatTypeService.getSeatTypeById(id));
    }


    @Override
    @ResponseStatus(HttpStatus.CREATED)
    public void createSeatType(SeatTypeDTO seatTypeDTO) {
        log.info("executed createSeatType() method");
        seatTypeService.saveSeatType(seatTypeMapper.toModel(seatTypeDTO));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    public void updateSeatType(SeatTypeDTO seatTypeDTO) {
        log.info("executed updateSeatType() method");
        seatTypeService.updateSeatType(seatTypeMapper.toModel(seatTypeDTO));
    }

    @Override
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSeatType(Long id) {
        log.info("executed deleteSeatType() method");
        seatTypeService.deleteSeatType(seatTypeService.getSeatTypeById(id));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    public List<SeatTypeDTO> getAllSeatTypesInAircraft(Long aircraftId) {
        log.info("executed getAllSeatTypes() method");
        return seatTypeService.getAllSeatTypesInAircraft(aircraftId)
                .stream()
                .map(seatTypeMapper::toDTO)
                .collect(Collectors.toList());
    }
}
