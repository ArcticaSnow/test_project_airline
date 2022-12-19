package ru.kataproject.p_sm_airlines_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kataproject.p_sm_airlines_1.entity.User;


/**
 * Репозиторий для работы с сущьностью User котора используется для организации аутентификации
 *
 * @author Toboe512
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
