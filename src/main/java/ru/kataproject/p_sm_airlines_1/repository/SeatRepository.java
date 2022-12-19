package ru.kataproject.p_sm_airlines_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kataproject.p_sm_airlines_1.entity.Seat;

import java.util.List;

/**
 * Implements Seat DAO via Spring Data JPA.
 *
 * @author Romanov Leonid (romanovsparta@ya.ru)
 * @since 07.10.2022
 */
@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> getSeatsByAircraft_Id(Long aircraftId);

    List<Seat> getSeatsByAircraft_IdAndSeatType_Id(Long aircraftId, Long seatTypeId);
}
