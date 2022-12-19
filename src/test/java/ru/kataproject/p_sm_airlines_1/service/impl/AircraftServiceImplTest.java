package ru.kataproject.p_sm_airlines_1.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.kataproject.p_sm_airlines_1.entity.Aircraft;
import ru.kataproject.p_sm_airlines_1.entity.Dto.AircraftDto;
import ru.kataproject.p_sm_airlines_1.entity.Seat;
import ru.kataproject.p_sm_airlines_1.entity.SeatCategory;
import ru.kataproject.p_sm_airlines_1.entity.SeatType;
import ru.kataproject.p_sm_airlines_1.service.AircraftService;
import ru.kataproject.p_sm_airlines_1.util.mapper.mapStruct.AircraftMapper;


import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;
import java.util.List;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.LinkedHashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * AircraftService test class.
 *
 * @author Alexey Sen (alexey.sen@gmail.com)
 * @since - 01.11.2022
 */
@SpringBootTest
@Slf4j
class AircraftServiceImplTest {

    @Autowired
    private AircraftService aircraftService;

    @Autowired
    private AircraftMapper aircraftMapper;

    private static final Random r = new Random();
    private static final Aircraft a1 = new Aircraft();
    private static final SeatType seatsType = new SeatType();
    private static final Set<Seat> collectionSeats = new HashSet<>();
    private static final List<Seat> listSeat = new ArrayList<>();

    @BeforeAll
    static void setUp() {
        a1.setModel("model-" + r.nextInt(1000));
        a1.setOnBoardNumber("board-" + r.nextInt(1000));
        a1.setStamp("stamp-" + r.nextInt(1000));
        a1.setYearOfRelease(2022);

        Seat seat = new Seat();
        seat.setSeatNumber("A" + r.nextInt(1000));
        seat.setSeatType(seatsType);
        seat.setAircraft(a1);
        collectionSeats.add(seat);
        a1.setSeats(collectionSeats);
        listSeat.add(seat);

        seatsType.setCategory(SeatCategory.BUSINESS);
        seatsType.setHasWindow(true);
        seatsType.setHasAdditPlace(true);
        seatsType.setHasTv(true);
        seatsType.setSeats(listSeat);
    }

    @Test
    void getAllAircrafts() {
        // given
        Aircraft a1 = new Aircraft();
        a1.setModel("model-" + r.nextInt(1000));
        a1.setOnBoardNumber("board-" + r.nextInt(1000));
        a1.setStamp("stamp-" + r.nextInt(1000));
        a1.setYearOfRelease(r.nextInt(1000));
        aircraftService.saveAircraft(a1);

        Aircraft a2 = new Aircraft();
        a2.setModel("model-" + r.nextInt(1000));
        a2.setOnBoardNumber("board-" + r.nextInt(1000));
        a2.setStamp("stamp-" + r.nextInt(1000));
        a2.setYearOfRelease(r.nextInt(1000));
        aircraftService.saveAircraft(a2);

        Aircraft a3 = new Aircraft();
        a3.setModel("model-" + r.nextInt(1000));
        a3.setOnBoardNumber("board-" + r.nextInt(1000));
        a3.setStamp("stamp-" + r.nextInt(1000));
        a3.setYearOfRelease(r.nextInt(1000));
        aircraftService.saveAircraft(a3);

        // when
        List<AircraftDto> list = aircraftService.getAllAircrafts();

        // then
        assertTrue(list.contains(aircraftMapper.toDto(a1)));
        assertTrue(list.contains(aircraftMapper.toDto(a2)));
        assertTrue(list.contains(aircraftMapper.toDto(a3)));
    }

    @Test
    @Transactional
    void saveAircraft() {
        // given

        // when
        Aircraft a2 = aircraftService.saveAircraft(a1);
        log.info("Aircraft saved.");
        Aircraft a3 = aircraftService.getAircraftById(a2.getId());

        // then
        assertThat(a3).isNotNull();
        assertThat(a3).isEqualTo(a2);
    }

    @Test
    @Transactional
    void updateAircraft() {
        // given

        // when
        Aircraft a2 = aircraftService.saveAircraft(a1);
        a2.setOnBoardNumber("onboard" + r.nextInt(1000));
        a2.setStamp("stamp" + r.nextInt(1000));
        a2.setModel("model+" + r.nextInt(1000));
        a2.setYearOfRelease(r.nextInt());
        a2.setSeats(new LinkedHashSet<>());
        aircraftService.updateAircraft(a2);

        Aircraft a3 = aircraftService.getAircraftById(a2.getId());

        // then
        assertThat(a3).isEqualTo(a2);
    }

    @Test
    @Transactional
    void getAircraftByRealId() {
        // given

        // when
        Aircraft a2 = aircraftService.saveAircraft(a1);
        Aircraft a3 = aircraftService.getAircraftById(a2.getId());

        // then
        assertThat(a3).isNotNull();
        assertThat(a3).isEqualTo(a2);
    }

    @Test
    void getAircraftByFakeId() {
        // given
        Long id = Long.valueOf(r.nextInt(1000000));

        // when
        try {
            aircraftService.getAircraftById(id);
        } catch (NoSuchElementException ex) {
            return;
        }
        // then
        fail("NoSuchElementException must be thrown on fake id=" + id);
    }

    @Test
    void delete() {
        // given

        // when
        Aircraft a = aircraftService.saveAircraft(a1);
        aircraftService.delete(a);
        try {
            aircraftService.getAircraftById(a.getId());
        } catch (NoSuchElementException ex) {
            return;
        }
        // then
        fail("NoSuchElementException must be thrown after deleting");
    }
}
