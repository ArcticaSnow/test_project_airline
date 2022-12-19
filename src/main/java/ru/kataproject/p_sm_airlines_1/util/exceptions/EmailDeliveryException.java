package ru.kataproject.p_sm_airlines_1.util.exceptions;

/**
 * EmailDeliveryException
 * @author Alexey Sen (alexey.sen@gmail.com)
 * @since 21.11.2022
 */
public class EmailDeliveryException extends MessageDeliveryException {

    public EmailDeliveryException(String address) {
        super(address);
    }

    public EmailDeliveryException(String address, String cause) {
        super(address, cause);
    }

    @Override
    protected String getResourceAlias() {
        return "Email";
    }
}
