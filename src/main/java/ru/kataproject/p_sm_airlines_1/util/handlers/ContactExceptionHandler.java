package ru.kataproject.p_sm_airlines_1.util.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.kataproject.p_sm_airlines_1.entity.Dto.ErrorResponseDto;
import ru.kataproject.p_sm_airlines_1.util.exceptions.ContactNotFoundException;

public class ContactExceptionHandler extends AbstractExceptionHandler{

    @ExceptionHandler(ContactNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> contactExceptionHandler(final ContactNotFoundException ex) {
        return buildErrorResponse(ex, HttpStatus.NOT_FOUND);
    }
}
