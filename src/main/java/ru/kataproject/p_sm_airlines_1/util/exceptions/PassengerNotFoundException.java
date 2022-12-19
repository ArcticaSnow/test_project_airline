package ru.kataproject.p_sm_airlines_1.util.exceptions;

public class PassengerNotFoundException extends AbstractResourceNotFoundException {

    public PassengerNotFoundException(Long id) {
        super(String.valueOf(id));
    }

    @Override
    protected String getResourceAlias() {
        return "Passenger";
    }
}
