package ru.kataproject.p_sm_airlines_1.service;

import org.keycloak.representations.AccessTokenResponse;
import ru.kataproject.p_sm_airlines_1.entity.Dto.AuthRequestDto;

/**
 *Интерфейс для организации работы с серверов авторизауии на базе KeyCloak
 *передставляет собой Rest Template клиент настроенные для работы с KeyCloak
 *
 * @author Toboe512
 */
public interface KeyCloakClientService {

    /**
     * Метод для получения Access Token по DTO c логином и паролем
     *
     * @param request AuthRequestDto данные для аутентификации логин и пароль
     * @return AccessTokenResponse
     */
    AccessTokenResponse authenticate(AuthRequestDto request);

    /**
     * Метод для получения Access Token с через refresh Token
     *
     * @param refreshToken String токен для обновление Access Token представленный ввиде строки символов
     * @return AccessTokenResponse
     */
    AccessTokenResponse refreshToken(String refreshToken);
}
