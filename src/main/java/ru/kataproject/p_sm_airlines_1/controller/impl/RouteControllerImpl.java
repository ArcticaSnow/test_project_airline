package ru.kataproject.p_sm_airlines_1.controller.impl;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.kataproject.p_sm_airlines_1.controller.RouteController;
import ru.kataproject.p_sm_airlines_1.entity.Dto.RouteDto;
import ru.kataproject.p_sm_airlines_1.entity.Route;
import ru.kataproject.p_sm_airlines_1.service.RouteService;
import ru.kataproject.p_sm_airlines_1.util.mapper.mapStruct.RouteMapper;

/**
 * controller for processing requests to add,
 * modify, and receive route data.
 *
 * @author Toboe512
 */
@Log4j2
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "swagger_auth")
public class RouteControllerImpl implements RouteController {
    private final RouteService routeService;
    private final RouteMapper routeMapper;

    @Override
    @ResponseStatus(HttpStatus.OK)
    public RouteDto getRouteById(Long id) {
        log.info("execute getRouteById method");
        return routeMapper.toDto(routeService.getRouteById(id));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    public void createRoute(RouteDto routeDto) {
        log.info("execute createRoute method");
        routeService.saveRoute(routeMapper.toModel(routeDto));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    public void updateRoute(RouteDto routeDto) {
        log.info("execute updateRoute method");
        routeService.updateRoute(routeMapper.toModel(routeDto));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    public void deleteRoute(Long id) {
        log.info("execute deleteRouteById method");
        Route route = routeService.getRouteById(id);
        routeService.deleteRoute(route);
    }
}

