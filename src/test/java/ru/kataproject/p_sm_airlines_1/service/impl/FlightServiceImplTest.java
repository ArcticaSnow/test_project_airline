package ru.kataproject.p_sm_airlines_1.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.kataproject.p_sm_airlines_1.entity.*;
import ru.kataproject.p_sm_airlines_1.repository.FlightRepository;
import ru.kataproject.p_sm_airlines_1.service.FlightService;
import ru.kataproject.p_sm_airlines_1.util.exceptions.FlightNotFoundException;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.Set;
import java.util.List;
import java.util.HashSet;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
@Slf4j
class FlightServiceImplTest {
    @Autowired
    private FlightService flightService;
    @Autowired
    private FlightRepository flightRepository;
    private final Random r = new Random();
    private Flight flightExpected;
    private Set<Seat> collectionSeats = new HashSet<>();
    private List<Seat> listSeat = new ArrayList<>();
    private SeatType seatType = new SeatType();

    @BeforeEach
    void setUp() {
        Destination destination1 = new Destination();
        destination1
                .setCity("city" + r.nextInt(1000))
                .setCountryCode("country" + r.nextInt(1000))
                .setTimezone(r.nextInt(1000))
                .setAirportName("airportName" + r.nextInt(1000))
                .setAirportCode("airportCode" + r.nextInt(1000))
                .setCountryName("countryName" + r.nextInt(1000));

        Destination destination2 = new Destination();
        destination2
                .setCity("city" + r.nextInt(1000))
                .setCountryCode("country" + r.nextInt(1000))
                .setTimezone(r.nextInt(1000))
                .setAirportName("airportName" + r.nextInt(1000))
                .setAirportCode("airportCode" + r.nextInt(1000))
                .setCountryName("countryName" + r.nextInt(1000));

        Aircraft aircraft = new Aircraft();
        aircraft.setOnBoardNumber("board-" + r.nextInt(1000));
        aircraft.setStamp("stamp-" + r.nextInt(1000));
        aircraft.setModel("model-" + r.nextInt(1000));
        aircraft.setYearOfRelease(2022);

        Seat seat = new Seat();
        seat.setSeatNumber("A" + r.nextInt(1000));
        seat.setSeatType(seatType);
        seat.setAircraft(aircraft);
        collectionSeats.add(seat);
        aircraft.setSeats(collectionSeats);
        listSeat.add(seat);

        seatType.setCategory(SeatCategory.BUSINESS);
        seatType.setHasWindow(true);
        seatType.setHasAdditPlace(true);
        seatType.setHasTv(true);
        seatType.setSeats(listSeat);

        flightExpected = new Flight();
        flightExpected
                .setDestinationFrom(destination1)
                .setDestinationTo(destination2)
                .setDepartureDateTime(LocalDateTime.now())
                .setArrivalDateTime(LocalDateTime.now())
                .setFlightStatus(FlightStatus.PLANNED)
                .setAircraft(aircraft)
                .setInflightServices("" + r.nextInt(1000));
    }

    @Test
    @Transactional
    void saveFlight() {
        // given

        // when
        flightService.saveFlight(flightExpected);

        // then
        Flight flightActual = flightRepository.getById(flightExpected.getId());
        assertThat(flightActual).isEqualTo(flightExpected);
    }

    @Test
    @Transactional
    void updateFlight() {
        log.info("updateFlight <-");
        // given
        flightService.saveFlight(flightExpected);
        Destination destination = new Destination();
        destination
                .setCity("city" + r.nextInt(1000))
                .setCountryCode("country" + r.nextInt(1000))
                .setTimezone(r.nextInt(1000))
                .setAirportName("airportName" + r.nextInt(1000))
                .setAirportCode("airportCode" + r.nextInt(1000))
                .setCountryName("countryName" + r.nextInt(1000));

        Aircraft aircraft = new Aircraft();
        aircraft.setModel("model-" + r.nextInt(1000));
        aircraft.setOnBoardNumber("board-" + r.nextInt(1000));
        aircraft.setStamp("stamp-" + r.nextInt(1000));
        aircraft.setYearOfRelease(2022);

        // when
        flightExpected
                .setFlightStatus(FlightStatus.CANCELLED)
                .setAircraft(aircraft)
                .setDepartureDateTime(LocalDateTime.now())
                .setDestinationTo(destination);
        log.info("updateFlight...");
        flightService.updateFlight(flightExpected);

        // then
        log.info("getById...");
        Flight flightActual = flightRepository.getById(flightExpected.getId());
        assertThat(flightActual).isEqualTo(flightExpected);
    }

    @Test
    @Transactional
    void getFlightByRealId() {
        // given
        flightService.saveFlight(flightExpected);

        // when
        Flight flightActual = flightService.getFlightById(flightExpected.getId());

        // then
        assertThat(flightActual).isEqualTo(flightExpected);
    }

    @Test
    void getFlightByFakeId() {
        // given

        // when
        try {
            flightService.getFlightById((long) r.nextInt(100000));
        } catch (FlightNotFoundException ex) {
            return;
        }

        // then
        fail("Exception FlightNotFoundException on fake flight id must be thrown");
    }

    @Test
    void delete() {
        // given
        flightService.saveFlight(flightExpected);
        flightService.getFlightById(flightExpected.getId());

        // when
        flightService.delete(flightExpected);
        try {
            flightService.getFlightById(flightExpected.getId());
        } catch (FlightNotFoundException ex) {
            return;
        }

        // then
        fail("Exception FlightNotFoundException on deleted flight id must be thrown");
    }
}
