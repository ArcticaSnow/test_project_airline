package ru.kataproject.p_sm_airlines_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kataproject.p_sm_airlines_1.entity.Contact;

/**
 * Interface ContactRepository.
 * Implements Contact DAO via Spring Data JPA.
 *
 * @author Ekaterina Kuchmistova (katy.shamina@yandex.ru)
 * @since 25.11.2022
 */
@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
}
