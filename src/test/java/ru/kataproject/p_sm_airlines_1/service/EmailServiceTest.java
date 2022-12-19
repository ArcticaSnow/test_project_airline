package ru.kataproject.p_sm_airlines_1.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.kataproject.p_sm_airlines_1.entity.Passenger;
import ru.kataproject.p_sm_airlines_1.util.exceptions.EmailDeliveryException;
import ru.kataproject.p_sm_airlines_1.util.exceptions.PassengerNotFoundException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

@Disabled("test doesn't work")
@SpringBootTest
@Slf4j
class EmailServiceTest {

    @Autowired
    EmailService emailService;

    @Autowired
    PassengerService passengerService;

    /**
     * Provide any valid email address that you can use to verify tests!!!
     */
    private static final String toAddress = "";

    private final String fileName = "classpath:email/attachment.txt";

    private final Random r = new Random();
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    private static List<Passenger> passengers = null;
    private static Passenger passengerWithInvalidEmail = null;
    private static Passenger passengerWithUnknownEmail = null;

    @BeforeAll
    static void init() {
        assertThat(toAddress).isNotBlank();
    }

    @BeforeEach
    void setUp() {
        if (passengers == null) {
            passengers = new ArrayList<>();
            // valid addresses
            Passenger passenger = new Passenger();
            passenger.setUsername(toAddress);
            passengerService.savePassenger(passenger);
            passengers.add(passenger);

            passenger = new Passenger();
            passenger.setUsername(toAddress);
            passengerService.savePassenger(passenger);
            passengers.add(passenger);

            passenger = new Passenger();
            passenger.setUsername(toAddress);
            passengerService.savePassenger(passenger);
            passengers.add(passenger);

            // unknown address
            passengerWithUnknownEmail = new Passenger();
            passengerWithUnknownEmail.setUsername("user." + r.nextInt(1000) + "@gmail.com");
            passengerService.savePassenger(passengerWithUnknownEmail);

            // invalid address
            passengerWithInvalidEmail = new Passenger();
            passengerWithInvalidEmail.setUsername("user@gmail.com" + r.nextInt(1000));
            passengerService.savePassenger(passengerWithInvalidEmail);
        }
    }

    @Test
    void sendSimpleEmail() {
        // given

        // when
        emailService.sendEmail(
                toAddress,
                "subject " + simpleDateFormat.format(new Date()),
                "message body " + simpleDateFormat.format(new Date()));

        // then
        // TODO getting email from imap service not implemented jet
    }

    @Test
    void sendSimpleEmailWithInvalidAddress() {
        // given

        // when
        try {
            emailService.sendEmail(
                    passengerWithInvalidEmail.getUsername(),
                    "subject " + simpleDateFormat.format(new Date()),
                    "message body " + simpleDateFormat.format(new Date()));
        } catch (EmailDeliveryException ex) {
            return;
        }

        // then
        fail("Email must not be sent to invalid address " + passengers.get(2).getUsername());
    }

    @Test
    void sendSimpleEmailToPassenger() {
        // given

        // when
        emailService.sendEmail(
                passengers.get(0),
                "subject " + simpleDateFormat.format(new Date()),
                "message body" + simpleDateFormat.format(new Date()));

        // then
        // TODO getting email from imap service not implemented jet
    }

    @Test
    void sendSimpleEmailToPassengerList() {
        // given

        // when
        emailService.sendEmail(
                passengers,
                "subject " + simpleDateFormat.format(new Date()),
                "message body" + simpleDateFormat.format(new Date()));

        // then
        // TODO getting email from imap service not implemented jet
    }

    @Test
    void sendEmailWithAttachment() {
        // given

        // when
        emailService.sendEmailWithAttachment(
                toAddress,
                "subject " + simpleDateFormat.format(new Date()),
                "message body " + simpleDateFormat.format(new Date()),
                fileName);

        // then
        // TODO getting email from imap service not implemented jet
    }

    @Test
    void sendEmailWithAttachmentToPassenger() {
        // given

        // when
        emailService.sendEmailWithAttachment(
                passengers.get(0),
                "subject " + simpleDateFormat.format(new Date()),
                "message body " + simpleDateFormat.format(new Date()),
                fileName);

        // then
        // TODO getting email from imap service not implemented jet
    }

    @Test
    void sendEmailWithAttachmentToPassengersList() {
        // given

        // when
        emailService.sendEmailWithAttachment(
                passengers,
                "subject " + simpleDateFormat.format(new Date()),
                "message body " + simpleDateFormat.format(new Date()),
                fileName);

        // then
        // TODO getting email from imap service not implemented jet
    }

    @Test
    void sendEmailToPassengerByIdWithAttachment() {
        // given

        // when
        emailService.sendEmailByPassengerId(
                passengers.get(0).getId(),
                "subject " + simpleDateFormat.format(new Date()),
                "message body " + simpleDateFormat.format(new Date()),
                fileName);

        // then
        // TODO getting email from imap service not implemented jet
    }

    @Test
    void sendEmailToPassengersListByIds() {
        // given
        List<Long> ids = new ArrayList<>();
        for (Passenger passenger : passengers) {
            ids.add(passenger.getId());
        }

        // when
        emailService.sendEmailByPassengerId(
                ids,
                "subject " + simpleDateFormat.format(new Date()),
                "message body " + simpleDateFormat.format(new Date()));

        // then
        // TODO getting email from imap service not implemented jet
    }

    @Test
    void sendEmailToPassengersListWithInvalidIds() {
        // given
        Long id = (long)r.nextInt(1000);
        List<Long> ids = new ArrayList<>();
        for (Passenger passenger : passengers) {
            ids.add(passenger.getId());
        }
        ids.add(id);

        // when
        try {
            emailService.sendEmailByPassengerId(
                    ids,
                    "subject " + simpleDateFormat.format(new Date()),
                    "message body " + simpleDateFormat.format(new Date()));
        } catch (PassengerNotFoundException ex) {
            log.info("Can't send email to passenger with invalid id=" + id);
            return;
        }

        // then
        fail("Must be thrown PassengerNotFoundException with id " + id);
    }
}