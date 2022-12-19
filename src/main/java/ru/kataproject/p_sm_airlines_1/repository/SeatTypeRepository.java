package ru.kataproject.p_sm_airlines_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kataproject.p_sm_airlines_1.entity.SeatType;

public interface SeatTypeRepository extends JpaRepository<SeatType, Long> {
}
