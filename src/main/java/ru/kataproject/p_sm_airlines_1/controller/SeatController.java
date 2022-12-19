package ru.kataproject.p_sm_airlines_1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.kataproject.p_sm_airlines_1.entity.Dto.ErrorResponseDto;
import ru.kataproject.p_sm_airlines_1.entity.Dto.SeatDto;

import java.util.List;

/**
 * Declares Seat API methods.
 *
 * @author Romanov Leonid (romanovsparta@ya.ru)
 * @since 13.10.2022
 */
@Tag(name = "Seat Controller")
@RequestMapping(SeatController.BASE_NAME)
public interface SeatController {
    /**
     * Major API version.
     */
    String MAJOR_VERSION = "/v1";
    /**
     * Base API name.
     */
    String BASE_NAME = MAJOR_VERSION + "/seat";


    /**
     * This method returns all Seats.
     *
     * @return List<SeatDto>
     */
    @GetMapping("/")
    @Operation(summary = "Getting all Seats")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Seats successfully returned", content = @Content),
            @ApiResponse(responseCode = "400", description = "Data not found", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDto.class)
            ))
    })
    List<SeatDto> getAllSeats();

    /**
     * This method returns Seat by ID.
     *
     * @param id Long
     * @return List<SeatDto>
     */
    @GetMapping("/{id}")
    @Operation(summary = "Getting Seat")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Seat Type successfully returned", content = @Content),
            @ApiResponse(responseCode = "400", description = "Data not found", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDto.class)
            ))
    })
    SeatDto getSeatById(@PathVariable Long id);

    /**
     * This method creates a new seat.
     *
     * @param seatDTO SeatDto
     */
    @PostMapping
    @Operation(summary = "Create seat")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Seat successfully created", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = SeatDto.class)

            )),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDto.class)
            ))
    })
    void createSeat(@RequestBody SeatDto seatDTO);

    /**
     * This method updates the seat.
     *
     * @param seatDTO SeatDto
     */
    @PutMapping
    @Operation(summary = "Update seat")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Seat successfully updated", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = SeatDto.class)
            )),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDto.class)
            ))
    })
    void updateSeat(@RequestBody SeatDto seatDTO);

    /**
     * This method deletes the seat by id.
     *
     * @param id Id
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete seat by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Seat successfully deleted", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = SeatDto.class)
            )),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDto.class)
            ))
    })
    void deleteSeat(@PathVariable("id") Long id);

    /**
     * This method returns all seats in the Aircraft by Aircraft id.
     *
     * @param aircraftId Long
     * @return List<SeatDto>
     */
    @GetMapping("/aircraft/{aircraft_id}")
    @Operation(summary = "Getting all seats in the Aircraft")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Seats successfully returned", content = @Content),
            @ApiResponse(responseCode = "400", description = "Data not found", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDto.class)
            ))
    })
    List<SeatDto> getAllSeatsInAircraft(@PathVariable("aircraft_id") Long aircraftId);

    /**
     * This method returns all seats of a certain type in the Aircraft by Aircraft id & Seat Type.
     *
     * @param aircraftId Long
     * @param seatTypeId Long
     * @return List<SeatDto>
     */
    @GetMapping("/aircraft/{aircraft_id}/category/{seat_type}")
    @Operation(summary = "Getting all seats of a certain category in the Aircraft")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Seats successfully returned", content = @Content),
            @ApiResponse(responseCode = "400", description = "Data not found", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDto.class)
            ))
    })
    List<SeatDto> getSeatByAircraftAndSeatTypeId(@PathVariable("aircraft_id") Long aircraftId, @PathVariable("seat_type") Long seatTypeId);
}
