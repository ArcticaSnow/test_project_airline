package ru.kataproject.p_sm_airlines_1.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kataproject.p_sm_airlines_1.entity.Route;
import ru.kataproject.p_sm_airlines_1.repository.RouteRepository;
import ru.kataproject.p_sm_airlines_1.service.RouteService;
import ru.kataproject.p_sm_airlines_1.util.exceptions.RouteNotFoundException;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;


    @Override
    public Route getRouteById(Long id) {
        Optional<Route> route = routeRepository.findById(id);
        return route.orElseThrow(RouteNotFoundException::new);
    }

    @Transactional
    @Override
    public void saveRoute(Route route) {
        routeRepository.save(route);
    }

    @Transactional
    @Override
    public void updateRoute(Route route) {
        routeRepository.save(route);
    }

    @Transactional
    @Override
    public void deleteRoute(Route route) {
        routeRepository.delete(route);
    }
}
