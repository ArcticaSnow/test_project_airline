package ru.kataproject.p_sm_airlines_1.controller.impl;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.kataproject.p_sm_airlines_1.controller.AircraftController;
import ru.kataproject.p_sm_airlines_1.entity.Dto.AircraftDto;
import ru.kataproject.p_sm_airlines_1.service.AircraftService;
import ru.kataproject.p_sm_airlines_1.util.mapper.mapStruct.AircraftMapper;

import java.util.List;

@RestController
@SecurityRequirement(name = "swagger_auth")
@RequiredArgsConstructor
@SecurityRequirement(name = "swagger_auth")
public class AircraftControllerImpl implements AircraftController {
    private final AircraftService aircraftService;
    private final AircraftMapper aircraftMapper;

    @Override
    public ResponseEntity<List<AircraftDto>> getAllAircrafts() {
        return new ResponseEntity<>(aircraftService.getAllAircrafts(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AircraftDto> getAircraftById(Long id) {
        AircraftDto aircraft = aircraftMapper.toDto(aircraftService.getAircraftById(id));
        return new ResponseEntity<>(aircraft, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpStatus> createAircraft(AircraftDto aircraft) {
        aircraftService.saveAircraft(aircraftMapper.toModel(aircraft));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpStatus> updateAircraft(AircraftDto aircraft) {
        aircraftService.updateAircraft(aircraftMapper.toModel(aircraft));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteAircraftById(Long id) {
        aircraftService.delete(aircraftService.getAircraftById(id));
        return ResponseEntity.ok(HttpStatus.OK);
    }
}

