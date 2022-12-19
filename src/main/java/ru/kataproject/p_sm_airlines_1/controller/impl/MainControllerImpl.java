package ru.kataproject.p_sm_airlines_1.controller.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.kataproject.p_sm_airlines_1.controller.MainController;

@RestController
public class MainControllerImpl implements MainController {

    @Override
    public ResponseEntity<String> index() {
        return new ResponseEntity<>("index", HttpStatus.OK);
    }
}
