package ru.kataproject.p_sm_airlines_1.util.exceptions;

public class AircraftNotFoundException extends AbstractResourceNotFoundException {
    public AircraftNotFoundException(Long id) {
        super(String.valueOf(id));
    }

    @Override
    protected String getResourceAlias() {
        return "Aircraft";
    }
}
