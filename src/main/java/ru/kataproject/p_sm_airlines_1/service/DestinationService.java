package ru.kataproject.p_sm_airlines_1.service;

import ru.kataproject.p_sm_airlines_1.entity.Destination;
import ru.kataproject.p_sm_airlines_1.entity.Dto.DestinationDTO;

import java.util.List;

public interface DestinationService {


    Destination getById(Long id);

    void update(Destination destination);

    void deleteById(Long id);

    void create(Destination destination);

    List<Destination> getByCity(String city);

    List<Destination> getByCountry(String countryName);
}