package ru.kataproject.p_sm_airlines_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kataproject.p_sm_airlines_1.entity.Route;

/**
 * Репозторий для работы с Route
 *
 * @author Toboe512
 */
@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
}
