package ru.kataproject.p_sm_airlines_1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kataproject.p_sm_airlines_1.entity.Dto.AircraftDto;

import java.util.List;

/**
 * Контроллер для обработки запросов по добавлению, изминению и получению данных о воздушном судне
 */

@Tag(name = "Aircrafts Controller", description = "Operations with aircrafts")
@RequestMapping(AircraftController.BASE_NAME)
public interface AircraftController {
    String MAJOR_VERSION = "/v1";
    String BASE_NAME = MAJOR_VERSION + "/aircrafts";

    @GetMapping
    @Operation(summary = "Get list of all aircrafts")
    @ApiResponse(responseCode = "200", description = "Page successfully returned")
    ResponseEntity<List<AircraftDto>> getAllAircrafts();

    /**
     * Получение воздушного судна по ID
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get aircrafts by id")
    @ApiResponse(responseCode = "200", description = "Page successfully returned")
    @ApiResponse(responseCode = "404", description = "Page not found")
    ResponseEntity<AircraftDto> getAircraftById(Long id);

    /**
     * Метод создания нового воздушного судна
     */
    @PostMapping
    @Operation(summary = "Create new aircraft")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(responseCode = "201", description = "Page successfully returned")
    @ApiResponse(responseCode = "405", description = "Method Not Allowed")
    ResponseEntity<HttpStatus> createAircraft(@RequestBody AircraftDto aircraft);

    /**
     * Метод изменения данных о воздушном судне
     */
    @PatchMapping
    @Operation(summary = "Update aircraft info")
    @ApiResponse(responseCode = "200", description = "Aircraft Information successfully updated")
    @ApiResponse(responseCode = "404", description = "Page not found")
    ResponseEntity<HttpStatus> updateAircraft(@RequestBody AircraftDto aircraft);

    /**
     * Метод удаления данных о воздушном судне
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete aircraft")
    @ApiResponse(responseCode = "204", description = "Aircraft successfully removed")
    @ApiResponse(responseCode = "404", description = "Page not found")
    ResponseEntity<HttpStatus> deleteAircraftById(@PathVariable Long id);
}
