package ru.kataproject.p_sm_airlines_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kataproject.p_sm_airlines_1.entity.TelegramMailings;

/**
 * Interface TelegramMailingsRepository.
 * Implements TelegramMailings DAO via Spring Data JPA.
 *
 * @author Ekaterina Kuchmistova (katy.shamina@yandex.ru)
 * @since 14.12.2022
 */
@Repository
public interface TelegramMailingsRepository extends JpaRepository<TelegramMailings, Long> {
}
