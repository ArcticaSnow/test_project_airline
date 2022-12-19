package ru.kataproject.p_sm_airlines_1.service;

import ru.kataproject.p_sm_airlines_1.entity.Passenger;

import javax.mail.MessagingException;
import java.util.List;

/**
 * Service API to send email to passengers.
 * @author Alexey Sen (alexe.sen@gmail.com)
 * @since 21.11.2022
 */
public interface EmailService {

    /**
     * Sends email to receiver.
     * @param toAddress string address of the receiver (To:). For example: user@domen.com
     * @param subject string email subject.
     * @param message string body of the message.
     */
    void sendEmail(final String toAddress, final String subject, final String message);

    /**
     * Sends email to passenger's email address.
     * @param toPassenger Passenger whose email will be used as receiver address (To:).
     * @param subject string email subject.
     * @param message string body of the message.
     */
    void sendEmail(final Passenger toPassenger, final String subject, final String message);

    /**
     * Sends emails to passenger's email addresses.
     * @param passengers is list of Passengers to send email.
     * @param subject string email subject.
     * @param message string body of the message.
     */
    void sendEmail(List<Passenger> passengers, final String subject, final String message);

    /**
     * Sends email to receiver.
     * @param toAddress string address of the receiver (To:). For example: user@domen.com
     * @param subject string email subject.
     * @param message string body of the message.
     * @param attachment String file name to attach to email
     * @throws MessagingException, FileNotFoundException.
     */
    void sendEmailWithAttachment(final String toAddress, final String subject, final String message, final String attachment);

    /**
     * Sends email with attachment to passenger's email addresses.
     * @param toPassenger Passenger to send email.
     * @param subject string email subject.
     * @param message string body of the message.
     * @param attachment String file name to attach to email
     * @throws MessagingException, FileNotFoundException.
     */
    void sendEmailWithAttachment(final Passenger toPassenger, final String subject, final String message, final String attachment);

    /**
     * Sends emails with attachment to passenger's email addresses.
     * @param passengers is list of Passengers to send email.
     * @param subject string email subject.
     * @param message string body of the message.
     * @param attachment String file name to attach to email.
     * @throws MessagingException, FileNotFoundException.
     */
    void sendEmailWithAttachment(List<Passenger> passengers, final String subject, final String message, final String attachment);

    /**
     * Send email to Passenger with id
     * @param passengerId passenger id;
     * @param subject email subject.
     * @param message body of the email message.
     */
    void sendEmailByPassengerId(Long passengerId, String subject, String message);

    /**
     * Send email to Passenger with id
     * @param passengerId passenger id;
     * @param subject email subject.
     * @param message body of the email message.
     * @param attachment filename to attache.
     */
    void sendEmailByPassengerId(Long passengerId, String subject, String message, String attachment);

    /**
     * Send email to several passengers via Bcc addresses.
     * @param ids list of passenger's id to send email
     * @param subject subject.
     * @param message message body.
     */
    void sendEmailByPassengerId(List<Long> ids, String subject, String message);
}
