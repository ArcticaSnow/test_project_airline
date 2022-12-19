package ru.kataproject.p_sm_airlines_1.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kataproject.p_sm_airlines_1.entity.Dto.AuthRequestDto;
import ru.kataproject.p_sm_airlines_1.entity.Dto.ErrorResponseDto;

/**
 *Контроллер для работы с токеном авторизации
 *
 * @author Toboe512
 */
@Tag(name = "Authenticate Controller")
@RequestMapping(AuthenticateController.BASE_NAME)
public interface AuthenticateController {

    String MAJOR_VERSION = "/v1";

    String BASE_NAME = MAJOR_VERSION + "/authenticate";

    /**
     * Метод получения токена авторизации по логину и паролю
     *
     * @param request DTO с логином и паролем
     * @return ResponseEntity<AccessTokenResponse>
     */
    @PostMapping
    @Operation(method = "POST", summary = "Get access token by username and password",
            description = "Get access token by username and password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Page successfully returned", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = AccessTokenResponse.class)
            )),
            @ApiResponse(responseCode = "400", description = "Page not found", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDto.class)
            ))
    })
    ResponseEntity<AccessTokenResponse> authenticate(@RequestBody AuthRequestDto request);

    /**
     * Метод обновления токена авторизации
     *
     * @param refreshToken токен для обновления авторизиционного токена
     * @return ResponseEntity<AccessTokenResponse>
     */
    @PostMapping("/refresh")
    @Operation(method = "POST", summary = "Refresh access token", description = "Refresh access token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Page successfully returned", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = AccessTokenResponse.class)
            )),
            @ApiResponse(responseCode = "400", description = "Page not found", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDto.class)
            ))
    })
    ResponseEntity<AccessTokenResponse> refresh(@RequestHeader("refresh-token") String refreshToken);
}
