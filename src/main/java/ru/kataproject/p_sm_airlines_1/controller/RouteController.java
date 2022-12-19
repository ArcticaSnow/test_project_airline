package ru.kataproject.p_sm_airlines_1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.kataproject.p_sm_airlines_1.entity.Dto.ErrorResponseDto;
import ru.kataproject.p_sm_airlines_1.entity.Dto.RouteDto;

/**
 * Контроллер для работы с Route
 *
 * @author Toboe512
 */
@Tag(name = "Route Controller")
@RequestMapping(RouteController.BASE_NAME)
public interface RouteController {
    String MAJOR_VERSION = "/v1";
    String BASE_NAME = MAJOR_VERSION + "/route";

    /**
     * Метод получения маршрута по id
     *
     * @param id Long
     * @return ResponseEntity<RouteDto>
     */
    @GetMapping
    @Operation(method = "GET", summary = "Get route by id", description = "Get route by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Page successfully returned", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = RouteDto.class)
            )),
            @ApiResponse(responseCode = "400", description = "Page not found", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDto.class)
            ))
    })
    RouteDto getRouteById(@PathVariable Long id);

    /**
     * Метод создания нового маршрута
     *
     * @param routeDto RouteDto
     */
    @PostMapping
    @Operation(method = "POST", summary = "Create route", description = "Create route")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Page successfully returned", content = @Content),
            @ApiResponse(responseCode = "400", description = "Page not found", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDto.class)
            ))
    })
    void createRoute(@RequestBody RouteDto routeDto);
    /**
     * Метода обновления маршрута
     *
     * @param routeDto RouteDto данные маршрута
     */
    @PatchMapping
    @Operation(method = "PATCH", summary = "Update route", description = "Update route")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Page successfully returned", content = @Content),
            @ApiResponse(responseCode = "400", description = "Page not found", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDto.class)
            ))
    })
    void  updateRoute(@RequestBody RouteDto routeDto);

    /**
     * Метод удаления мартшрута
     *
     * @param id Long
     */
    @DeleteMapping("/{id}")
    @Operation(method = "DELETE", summary = "Delete route by id", description = "Delete route by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Page successfully returned", content = @Content),
            @ApiResponse(responseCode = "400", description = "Page not found", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDto.class)
            ))
    })
    void deleteRoute(@PathVariable("id") Long id);

}
