package ru.kataproject.p_sm_airlines_1.entity;

/**
 * Перечисление полётного статуса - "По плану", "Задержан", "Отменён".
 * <p>
 * (C)Toboe512
 */
public enum FlightStatus {
    PLANNED("The flight will proceed as planned."),
    DELAYED("Flight delayed, sorry."),
    CANCELLED("Flight canceled.");

    private final String status;

    FlightStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "FlightStatus{" +
                "status='" + status + '\'' +
                '}';
    }
}
