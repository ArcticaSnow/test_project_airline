package ru.kataproject.p_sm_airlines_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kataproject.p_sm_airlines_1.entity.Document;

/**
 * Interface DocumentRepository.
 * Implements Document DAO via Spring Data JPA.
 *
 * @author Mishin Yura (mishin.inbox@gmail.com)
 * @since 10.10.2022
 */
@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
}
