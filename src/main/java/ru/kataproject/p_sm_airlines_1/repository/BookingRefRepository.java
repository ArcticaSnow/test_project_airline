package ru.kataproject.p_sm_airlines_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kataproject.p_sm_airlines_1.entity.BookingRef;

/**
 * Interface BookingRefRepository.
 * Implements BookingRef DAO via Spring Data JPA.
 *
 * @author Alexey Sen (alexey.sen@gmail.com)
 * @since 31.10.2022
 */
@Repository
public interface BookingRefRepository extends JpaRepository<BookingRef, Long> {
    BookingRef findByRefNumber(String refNumber);
}
