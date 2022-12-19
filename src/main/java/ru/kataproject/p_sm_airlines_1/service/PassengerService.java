package ru.kataproject.p_sm_airlines_1.service;

import ru.kataproject.p_sm_airlines_1.entity.Dto.PassengerDto;
import ru.kataproject.p_sm_airlines_1.entity.Passenger;

import java.util.List;

public interface PassengerService {

    List<PassengerDto> getAllPassengers();

    Passenger getPassengerById(Long id);

    void updatePassenger(Passenger updatedPassenger);

    void savePassenger(Passenger passenger);

    void deletePassengerById(Long id);
}
