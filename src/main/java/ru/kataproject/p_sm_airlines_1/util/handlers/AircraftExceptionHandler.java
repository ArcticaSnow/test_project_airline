package ru.kataproject.p_sm_airlines_1.util.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.kataproject.p_sm_airlines_1.entity.Dto.ErrorResponseDto;
import ru.kataproject.p_sm_airlines_1.util.exceptions.AircraftNotFoundException;

@ControllerAdvice
public class AircraftExceptionHandler extends AbstractExceptionHandler {
    @ExceptionHandler(AircraftNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> aircraftExceptionHandler(final AircraftNotFoundException ex) {
        return buildErrorResponse(ex, HttpStatus.NOT_FOUND);
    }

}
