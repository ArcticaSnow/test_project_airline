package ru.kataproject.p_sm_airlines_1.util.exceptions;

/**
 * Abstract class to extend info about message delivery fails via email, whatsapp, telegram etc.
 * @author Alexey Sen (alexey.sen@gmail.com)
 * @since 21.11.2022
 */
public abstract class MessageDeliveryException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final String message;

    protected MessageDeliveryException(final String resourceId, final String cause) {
        this.message = buildMessage(resourceId, cause);
    }

    protected MessageDeliveryException(final String resourceId) {
        this.message = buildMessage(resourceId);
    }

    protected MessageDeliveryException() {
        this.message = buildMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }

    private String buildMessage(final String resourceId, final String cause) {
        return  String.format("%s to '%s' is not delivered: %s.", getResourceAlias(), resourceId, cause);
    }

    private String buildMessage(final String resourceId) {
        return  String.format("%s to '%s' is not delivered.", getResourceAlias(), resourceId);
    }

    private String buildMessage() {
        return String.format("%s is not delivered.", getResourceAlias());
    }

    protected abstract String getResourceAlias();
}
