package ru.kataproject.p_sm_airlines_1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kataproject.p_sm_airlines_1.entity.Dto.DestinationDTO;
import ru.kataproject.p_sm_airlines_1.entity.Dto.ErrorResponseDto;

import java.util.List;

@Tag(name = "The destination API")
@RequestMapping(DestinationController.BASE_NAME)
public interface DestinationController {
    String MAJOR_VERSION = "/v1";
    String DESTINATION = "/destinations";
    String BASE_NAME = MAJOR_VERSION + "/main" + DESTINATION;

    @PostMapping
    @Operation(method = "POST", summary = "Create destination", description = "Create destination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Destinations is found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Destinations not found", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDto.class)
            ))
    })

    ResponseEntity<HttpStatus> create(@RequestBody DestinationDTO destination);

    @GetMapping("/id")
    @Operation(method = "GET", summary = "Get destination by id", description = "Return destination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Destinations is found", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = DestinationDTO.class)
            )),
            @ApiResponse(responseCode = "400", description = "Destinations not found", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDto.class)
            ))
    })

    ResponseEntity<DestinationDTO> getById(@RequestParam Long id);

    @PutMapping
    @Operation(method = "PUT", summary = "Update destination", description = "Update destination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Destinations is found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Destinations not found", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDto.class)
            ))
    })
    ResponseEntity<HttpStatus> update(@RequestBody DestinationDTO destination);

    @DeleteMapping("/id")
    @Operation(method = "DELETE", summary = "Delete destination by id", description = "Delete destination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Destinations is found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Destinations not found", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDto.class)
            ))
    })
    ResponseEntity<HttpStatus> deleteById(@PathVariable Long id);

    @GetMapping("/city")
    @Operation(method = "GET", summary = "Get destinations by city", description = "Return list of destinations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Destinations is found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Destinations not found", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDto.class)
            ))
    })
      List<DestinationDTO> getByCity(@RequestParam String city);
    @GetMapping("/country")
    @Operation(method = "GET", summary = "Get destinations by country", description = "Return list of destinations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Destinations is found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Destinations not found", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDto.class)
            ))
    })
    List<DestinationDTO> getByCountry(@RequestParam String country);
}