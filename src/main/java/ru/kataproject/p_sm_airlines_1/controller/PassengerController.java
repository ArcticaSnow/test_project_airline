package ru.kataproject.p_sm_airlines_1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kataproject.p_sm_airlines_1.entity.Dto.PassengerDto;

import java.util.List;

/**
 * NB! content type specified in application.yml file
 * springdoc: default-produces-media-type: application/json
 */

@Tag(name = "Passengers Controller", description = "Operations with passengers")
@RequestMapping(PassengerController.BASE_NAME)
public interface PassengerController {
    String MAJOR_VERSION = "/v1";
    String BASE_NAME = MAJOR_VERSION + "/passengers";

    /**
     * Метод возвращает список всех пассажиров из БД. The method returns a list of all passengers from DB.
     */
    @GetMapping
    @Operation(summary = "Get list of all passengers")
    @ApiResponse(responseCode = "200", description = "Page successfully returned")
    ResponseEntity<List<PassengerDto>> getAllPassengers();

    /**
     * Метод возвращает пассажира по Id. The method gets/returns a passenger by Id.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get passenger by id")
    @ApiResponse(responseCode = "200", description = "Page successfully returned")
    @ApiResponse(responseCode = "404", description = "Page not found")
    ResponseEntity<PassengerDto> getPassengerById(@PathVariable Long id);

    /**
     * Метод создаёт новый Data Transfer Object (DTO) passenger. The method create a passenger.
     */
    @PostMapping
    @Operation(summary = "Create new passenger")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(responseCode = "201", description = "Page successfully returned")
    @ApiResponse(responseCode = "405", description = "Method Not Allowed")
    ResponseEntity<PassengerDto> createPassenger(@RequestBody PassengerDto passengerDto);

    /**
     * Метод редактирует информацию о пассажире. The method update passenger information.
     */
    @PatchMapping
    @Operation(summary = "Update passenger info")
    @ApiResponse(responseCode = "200", description = "Passenger Information successfully updated")
    @ApiResponse(responseCode = "404", description = "Page not found")
    ResponseEntity<PassengerDto> updatePassenger(@RequestBody PassengerDto passengerDto);

    /**
     * Метод удаляет пассажира из базы данных. The method removes the passenger from the database.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete passenger")
    @ApiResponse(responseCode = "204", description = "Passenger successfully removed")
    @ApiResponse(responseCode = "404", description = "Page not found")
    ResponseEntity<Void> deletePassengerById(@PathVariable Long id);

}
