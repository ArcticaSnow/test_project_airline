package ru.kataproject.p_sm_airlines_1.util.exceptions;

public class DestinationNotFoundException extends AbstractResourceNotFoundException {
    @Override
    protected String getResourceAlias() {
        return "Destination";
    }
}
