package ru.kataproject.p_sm_airlines_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kataproject.p_sm_airlines_1.entity.Flight;

/**
 * Репозиторий для работы с сущностью Flight
 *
 * @author Toboe512
 */
@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
}
