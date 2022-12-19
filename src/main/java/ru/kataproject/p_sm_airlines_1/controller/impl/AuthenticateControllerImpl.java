package ru.kataproject.p_sm_airlines_1.controller.impl;

import lombok.RequiredArgsConstructor;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ru.kataproject.p_sm_airlines_1.controller.AuthenticateController;
import ru.kataproject.p_sm_airlines_1.entity.Dto.AuthRequestDto;
import ru.kataproject.p_sm_airlines_1.service.KeyCloakClientService;

/**
 *Контроллер для работы с токеном авторизации
 *
 * @author Toboe512
 */
@RestController
@RequiredArgsConstructor
public class AuthenticateControllerImpl implements AuthenticateController {

    private final KeyCloakClientService keyCloakClientService;

    @Override
    public ResponseEntity<AccessTokenResponse> authenticate(AuthRequestDto request) {
        return ResponseEntity.ok(keyCloakClientService.authenticate(request));
    }

    @Override
    public ResponseEntity<AccessTokenResponse> refresh(@RequestHeader("refresh-token") String refreshToken) {
        return ResponseEntity.ok(keyCloakClientService.refreshToken(refreshToken));
    }
}
