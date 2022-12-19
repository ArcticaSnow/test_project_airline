package ru.kataproject.p_sm_airlines_1.util.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.kataproject.p_sm_airlines_1.entity.Dto.ErrorResponseDto;

/**
 *
 * @author Toboe512
 */
@ControllerAdvice
public class AuthenticationCredentialsNotFoundHandler extends AbstractExceptionHandler {

    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> authenticationExceptionHandler(final AuthenticationCredentialsNotFoundException ex) {
        return buildErrorResponse(ex, HttpStatus.UNAUTHORIZED);
    }
}
