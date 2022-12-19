package ru.kataproject.p_sm_airlines_1.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.kataproject.p_sm_airlines_1.controller.EmailController;
import ru.kataproject.p_sm_airlines_1.service.EmailService;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
public class EmailControllerImpl implements EmailController {

    private final EmailService emailService;

    @Override
    @ResponseStatus(HttpStatus.OK)
    public void sendSimpleEmail(String email, String subject, String body) {
        log.info("execute sendSimpleEmail method");
        emailService.sendEmail(email, subject, body);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    public void sendEmail(Long id, String subject, String attachment, String body) {
        log.info("execute sendEmail method");
        if (attachment == null) {
            emailService.sendEmailByPassengerId(id, subject, body);
        } else {
            emailService.sendEmailByPassengerId(id, subject, body, attachment);
        }
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    public void sendEmail(List<Long> ids, String subject, String body) {
        log.info("execute sendEmails method");
        emailService.sendEmailByPassengerId(ids, subject, body);
    }
}
