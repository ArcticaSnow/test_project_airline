package ru.kataproject.p_sm_airlines_1.util.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.kataproject.p_sm_airlines_1.entity.Dto.ErrorResponseDto;
import ru.kataproject.p_sm_airlines_1.util.exceptions.DestinationNotFoundException;

@ControllerAdvice
public class DestinationExceptionHandler extends AbstractExceptionHandler {

    @ExceptionHandler(DestinationNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> destinationNotFoundException(DestinationNotFoundException destinationNotFoundException) {
        return buildErrorResponse(destinationNotFoundException, HttpStatus.NOT_FOUND);
    }
}