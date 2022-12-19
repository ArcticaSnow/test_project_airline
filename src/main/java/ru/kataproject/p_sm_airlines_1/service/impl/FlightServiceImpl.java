package ru.kataproject.p_sm_airlines_1.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kataproject.p_sm_airlines_1.entity.Flight;
import ru.kataproject.p_sm_airlines_1.repository.DestinationRepository;
import ru.kataproject.p_sm_airlines_1.repository.FlightRepository;
import ru.kataproject.p_sm_airlines_1.service.FlightService;
import ru.kataproject.p_sm_airlines_1.util.exceptions.FlightNotFoundException;

import java.util.Optional;

/**
 * Сервис для работы с бизнеслогикой рейса
 *
 * @author Toboe512
 * @author Alexey Sen (alexey.sen@gmail.com)
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final DestinationRepository destinationRepository;

    @Transactional
    @Override
    public void saveFlight(Flight flight) {
        if (flight == null) return;
        if (flight.getDestinationTo().getId() == null) {
            flight.setDestinationTo(destinationRepository.saveAndFlush(flight.getDestinationTo()));
        } else {
            flight.setDestinationTo(destinationRepository.findById(flight.getDestinationTo().getId()).orElseThrow());
        }
        if (flight.getDestinationFrom().getId() == null) {
            flight.setDestinationFrom(destinationRepository.saveAndFlush(flight.getDestinationFrom()));
        } else {
            flight.setDestinationFrom(destinationRepository.findById(flight.getDestinationFrom().getId()).orElseThrow());
        }
        flightRepository.save(flight);
    }

    @Transactional
    @Override
    public void updateFlight(Flight flight) {
        flightRepository.save(flight);
    }

    @Override
    public Flight getFlightById(Long id) {
        Optional<Flight> flight = flightRepository.findById(id);
        return flight.orElseThrow(FlightNotFoundException::new);
    }

    @Override
    @Transactional
    public void delete(Flight flight) {
        flightRepository.delete(flight);
    }
}