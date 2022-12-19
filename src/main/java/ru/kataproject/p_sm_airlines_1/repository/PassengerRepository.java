package ru.kataproject.p_sm_airlines_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kataproject.p_sm_airlines_1.entity.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
