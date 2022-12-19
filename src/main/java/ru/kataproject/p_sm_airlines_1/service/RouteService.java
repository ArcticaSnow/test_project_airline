package ru.kataproject.p_sm_airlines_1.service;
import ru.kataproject.p_sm_airlines_1.entity.Route;


/**
 * Сервис для работы с Route
 *
 * @author Toboe512
 */
public interface RouteService {

    Route getRouteById(Long id);

    void saveRoute(Route route);

    void updateRoute(Route route);

    void deleteRoute(Route route);
}
