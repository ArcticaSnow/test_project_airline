package ru.kataproject.p_sm_airlines_1.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kataproject.p_sm_airlines_1.entity.Destination;
import ru.kataproject.p_sm_airlines_1.repository.DestinationRepository;
import ru.kataproject.p_sm_airlines_1.service.DestinationService;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DestinationServiceImpl implements DestinationService {

    private final DestinationRepository destinationRepository;


    @Transactional
    @Override
    public void create(Destination destination) {
        destinationRepository.saveAndFlush(destination);
    }

    @Override
    public Destination getById(Long id) {
        return destinationRepository.findById(id).get();
    }

    @Transactional
    @Override
    public void update(Destination destination) {
        destinationRepository.saveAndFlush(destination);
    }

    @Override
    public void deleteById(Long id) {
        destinationRepository.deleteById(id);
    }

    @Transactional
    @Override
    public List<Destination> getByCity(String city) {
        return destinationRepository.getByCity(city);
    }

    @Transactional
    @Override
    public List<Destination> getByCountry(String countryName) {
        return destinationRepository.getByCountry(countryName);
    }
}
