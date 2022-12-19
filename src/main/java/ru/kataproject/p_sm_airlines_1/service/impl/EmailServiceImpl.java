package ru.kataproject.p_sm_airlines_1.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import ru.kataproject.p_sm_airlines_1.entity.Passenger;
import ru.kataproject.p_sm_airlines_1.service.EmailService;
import ru.kataproject.p_sm_airlines_1.service.PassengerService;
import ru.kataproject.p_sm_airlines_1.util.exceptions.EmailDeliveryException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Service sends email to passengers.
 * @author Alexey Sen (alexe.sen@gmail.com)
 * @since 21.11.2022
 */
@Service
@Slf4j
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;
    private final PassengerService passengerService;

    @Override
    public void sendEmail(String toAddress, String subject, String message) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        if (EmailValidator.getInstance().isValid(toAddress)) {
            simpleMailMessage.setTo(toAddress);
        } else {
            throw new EmailDeliveryException(toAddress, "invalid address");
        }
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        emailSender.send(simpleMailMessage);
    }

    @Override
    public void sendEmail(Passenger toPassenger, String subject, String message) {
        if (toPassenger.getUsername() == null) {
            return;
        }
        sendEmail(toPassenger.getUsername(), subject, message);
    }

    @Override
    public void sendEmail(List<Passenger> toPassengers, String subject, String message) {
        for (Passenger passenger : toPassengers) {
            sendEmail(passenger, subject, message);
        }
    }

    @Override
    public void sendEmailWithAttachment(String toAddress, String subject, String message, String attachment) {
        try {
            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            if (EmailValidator.getInstance().isValid(toAddress)) {
                messageHelper.setTo(toAddress);
            } else {
                throw new EmailDeliveryException(toAddress, "invalid address");
            }
            messageHelper.setSubject(subject);
            messageHelper.setText(message);
            FileSystemResource file = new FileSystemResource(ResourceUtils.getFile(attachment));
            messageHelper.addAttachment("Originated file name: " + attachment, file);
            emailSender.send(mimeMessage);
        } catch (MessagingException | FileNotFoundException ex) {
            throw new EmailDeliveryException(toAddress, ex.getMessage());
        }
    }

    @Override
    public void sendEmailWithAttachment(Passenger toPassenger, String subject, String message, String attachment) {
        if (toPassenger.getUsername() == null) {
            return;
        }
        sendEmailWithAttachment(toPassenger.getUsername(), subject, message, attachment);
    }

    @Override
    public void sendEmailWithAttachment(List<Passenger> toPassengers, String subject, String message, String attachment) {
        for (Passenger passenger : toPassengers) {
            sendEmailWithAttachment(passenger.getUsername(), subject, message, attachment);
        }
    }

    @Override
    public void sendEmailByPassengerId(Long passengerId, String subject, String message) {
        Passenger passenger = passengerService.getPassengerById(passengerId);
        if (passenger.getUsername() == null) {
            return;
        }
        sendEmail(passenger.getUsername(), subject, message);
    }

    @Override
    public void sendEmailByPassengerId(Long passengerId, String subject, String message, String attachment) {
        Passenger passenger = passengerService.getPassengerById(passengerId);
        if (passenger.getUsername() == null) {
            return;
        }
        sendEmailWithAttachment(passenger.getUsername(), subject, message, attachment);
    }

    @Override
    @Transactional
    public void sendEmailByPassengerId(List<Long> ids, String subject, String message) {
        log.debug("sendEmailByPassengerId: <- ids=" + ids + ", subject='" + subject + "', message='" + message+"'");
        ArrayList<String> bcc = new ArrayList<>();
        for (Long id : ids) {
            Passenger passenger = passengerService.getPassengerById(id);
            if (passenger.getUsername() != null) {
                if (EmailValidator.getInstance().isValid(passenger.getUsername())) {
                    bcc.add(passenger.getUsername());
                } else {
                    throw new EmailDeliveryException(passenger.getUsername(), "invalid address");
                }
            }
        }

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setBcc(bcc.toArray(new String[0]));
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        emailSender.send(simpleMailMessage);
    }
}
