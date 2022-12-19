package ru.kataproject.p_sm_airlines_1.util.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.kataproject.p_sm_airlines_1.entity.Dto.ErrorResponseDto;
import ru.kataproject.p_sm_airlines_1.util.exceptions.EmailDeliveryException;

public class EmailExceptionHandler extends AbstractExceptionHandler {
    @ExceptionHandler(EmailDeliveryException.class)
    public ResponseEntity<ErrorResponseDto> emailExceptionHandler(final EmailDeliveryException ex) {
        return buildErrorResponse(ex, HttpStatus.BAD_REQUEST);
    }
}
