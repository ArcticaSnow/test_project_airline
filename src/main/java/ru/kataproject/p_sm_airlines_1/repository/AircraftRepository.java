package ru.kataproject.p_sm_airlines_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kataproject.p_sm_airlines_1.entity.Aircraft;

/**
 * Репозиторий для работы с сущностью Aircraft
 */

public interface AircraftRepository extends JpaRepository<Aircraft, Long> {
}
