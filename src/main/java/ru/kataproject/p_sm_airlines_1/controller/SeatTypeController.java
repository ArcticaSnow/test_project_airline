package ru.kataproject.p_sm_airlines_1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.kataproject.p_sm_airlines_1.entity.Dto.ErrorResponseDto;
import ru.kataproject.p_sm_airlines_1.entity.Dto.SeatTypeDTO;

import java.util.List;

@Tag(name = "SeatType Controller")
@RequestMapping(SeatTypeController.BASE_NAME)
public interface SeatTypeController {
    /**
     * Major API version.
     */
    String MAJOR_VERSION = "/v1";
    /**
     * Base API name.
     */
    String BASE_NAME = MAJOR_VERSION + "/seat_type";

    /**
     * This method returns all Seat Types.
     *
     * @return List<SeatTypeDTO>
     */
    @GetMapping("/")
    @Operation(summary = "Getting all Seat Types")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Seat Types successfully returned", content = @Content),
            @ApiResponse(responseCode = "400", description = "Data not found", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDto.class)
            ))
    })
    List<SeatTypeDTO> getAllSeatTypes();

    /**
     * This method returns Seat Type by ID.
     *
     * @param id Long
     * @return List<SeatTypeDTO>
     */
    @GetMapping("/{id}")
    @Operation(summary = "Getting Seat Type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Seat Type successfully returned", content = @Content),
            @ApiResponse(responseCode = "400", description = "Data not found", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDto.class)
            ))
    })
    SeatTypeDTO getSeatTypeById(Long id);

    /**
     * This method creates a new seat.
     *
     * @param seatTypeDTO SeatTypeDTO
     */
    @PostMapping
    @Operation(summary = "Create Seat Type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Seat Type successfully created", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = SeatTypeDTO.class)

            )),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDto.class)
            ))
    })
    void createSeatType(@RequestBody SeatTypeDTO seatTypeDTO);

    /**
     * This method updates the seat.
     *
     * @param seatTypeDTO SeatTypeDTO
     */
    @PutMapping
    @Operation(summary = "Update Seat Type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Seat Type successfully updated", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = SeatTypeDTO.class)
            )),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDto.class)
            ))
    })
    void updateSeatType(@RequestBody SeatTypeDTO seatTypeDTO);

    /**
     * This method deletes the seat by id.
     *
     * @param id Id
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Seat Type by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Seat successfully deleted", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = SeatTypeDTO.class)
            )),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDto.class)
            ))
    })
    void deleteSeatType(@PathVariable("id") Long id);

    /**
     * This method returns all Seat Types in the Aircraft by Aircraft id.
     *
     * @param aircraftId Long
     * @return List<SeatTypeDTO>
     */
    @GetMapping("/{aircraft_id}")
    @Operation(summary = "Getting all Seat Types in the Aircraft")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Seat Types successfully returned", content = @Content),
            @ApiResponse(responseCode = "400", description = "Data not found", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDto.class)
            ))
    })
    List<SeatTypeDTO> getAllSeatTypesInAircraft(@PathVariable("aircraft_id") Long aircraftId);

}
