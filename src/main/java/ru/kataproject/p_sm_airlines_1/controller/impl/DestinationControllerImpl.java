package ru.kataproject.p_sm_airlines_1.controller.impl;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.kataproject.p_sm_airlines_1.controller.DestinationController;
import ru.kataproject.p_sm_airlines_1.entity.Dto.DestinationDTO;
import ru.kataproject.p_sm_airlines_1.service.impl.DestinationServiceImpl;
import ru.kataproject.p_sm_airlines_1.util.mapper.mapStruct.DestinationMapper;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@SecurityRequirement(name = "swagger_auth")
@RequiredArgsConstructor
public class DestinationControllerImpl implements DestinationController {
    private final DestinationServiceImpl destinationService;
    private final DestinationMapper destinationMapper;


    @Override
    public ResponseEntity<HttpStatus> create(DestinationDTO destination) {
        destinationService.create(destinationMapper.toModel(destination));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DestinationDTO> getById(Long id) {
        DestinationDTO destination = destinationMapper.toDto(destinationService.getById(id));
        return new ResponseEntity<>(destination, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpStatus> update(DestinationDTO destination) {
        destinationService.update(destinationMapper.toModel(destination));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteById(Long id) {
        destinationService.deleteById(destinationService.getById(id).getId());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Override
    public List<DestinationDTO> getByCity(String city) {
        return destinationService.getByCountry(city).stream().map(destinationMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<DestinationDTO> getByCountry(String country) {
        return destinationService.getByCountry(country).stream().map(destinationMapper::toDto).collect(Collectors.toList());
    }
}
