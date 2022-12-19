package ru.kataproject.p_sm_airlines_1.service.impl;


import org.keycloak.representations.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.kataproject.p_sm_airlines_1.entity.Dto.AuthRequestDto;
import ru.kataproject.p_sm_airlines_1.service.KeyCloakClientService;

/**
 *Класс для организации работы с серверов авторизауии на базе KeyCloak
 * передставляет собой Rest Template клиент настроенные для работы с KeyCloak
 *
 * @author Toboe512
 */
@Service
public class KeyCloakClientServiceImpl implements KeyCloakClientService {
    @Value("${keycloak.auth-server-url}")
    private String keyCloakUrl;
    @Value("${keycloak.resource}")
    private String clientId;
    @Value("${keycloak.realm}")
    private String realm;

    private final RestTemplate restTemplate;

    public KeyCloakClientServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public AccessTokenResponse authenticate(AuthRequestDto request) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("client_id", clientId);
        parameters.add("grant_type", "password");
        parameters.add("scope", "openid");
        parameters.add("username", request.getUsername());
        parameters.add("password", request.getPassword());

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(parameters, headers);

        AccessTokenResponse accessTokenResponse;
        try {
            accessTokenResponse = restTemplate.exchange(getAuthUrl(),
                    HttpMethod.POST,
                    entity,
                    AccessTokenResponse.class).getBody();

        } catch (HttpClientErrorException e) {
            throw new AuthenticationCredentialsNotFoundException("Invalid user credentials");
        }
        return accessTokenResponse;
    }

    @Override
    public AccessTokenResponse refreshToken(String refreshToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("grant_type", "refresh_token");
        parameters.add("client_id", clientId);
        parameters.add("refresh_token", refreshToken);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(parameters, headers);

        AccessTokenResponse accessTokenResponse;
        try {
            accessTokenResponse = restTemplate.exchange(getAuthUrl(),
                    HttpMethod.POST,
                    entity,
                    AccessTokenResponse.class).getBody();

        } catch (HttpClientErrorException e) {
            throw new AuthenticationCredentialsNotFoundException("Invalid user credentials");
        }
        return accessTokenResponse;
    }

    private String getAuthUrl() {
        return UriComponentsBuilder.fromHttpUrl(keyCloakUrl)
                .pathSegment("realms")
                .pathSegment(realm)
                .pathSegment("protocol")
                .pathSegment("openid-connect")
                .pathSegment("token")
                .toUriString();
    }
}
