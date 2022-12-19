package ru.kataproject.p_sm_airlines_1.util.mapper.mapStruct;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.kataproject.p_sm_airlines_1.entity.Aircraft;
import ru.kataproject.p_sm_airlines_1.entity.Dto.SeatDto;
import ru.kataproject.p_sm_airlines_1.entity.Seat;
import ru.kataproject.p_sm_airlines_1.entity.SeatCategory;
import ru.kataproject.p_sm_airlines_1.entity.SeatType;
import ru.kataproject.p_sm_airlines_1.repository.AircraftRepository;
import ru.kataproject.p_sm_airlines_1.repository.SeatTypeRepository;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * SeatMapper test class.
 *
 * @author Alexey Sen (alexey.sen@gmail.com)
 * @since - 01.11.2022
 */
@SpringBootTest
public class SeatMapperTest {
    @Autowired
    private SeatMapper seatMapper;
    @Autowired
    private AircraftRepository aircraftRepository;
    @Autowired
    private SeatTypeRepository seatTypeRepository;
    private SeatDto seatDtoExpected;
    private Seat seatExpected;

    @BeforeEach
    void setUp() {
        Aircraft aircraft = new Aircraft();
        aircraft.setOnBoardNumber("number");
        aircraft.setStamp("stamp");
        aircraft.setModel("model");
        aircraft.setYearOfRelease(2020);

        aircraftRepository.save(aircraft);

        SeatType seatType = new SeatType();
        seatType.setCategory(SeatCategory.BUSINESS);
        seatType.setHasWindow(true);
        seatType.setHasAdditPlace(true);
        seatType.setHasTv(true);

        seatTypeRepository.save(seatType);

        seatDtoExpected = new SeatDto(
                1L, "seatNumber", seatType.getId(), aircraft.getId()
        );

        seatExpected = new Seat();
        seatExpected.setId(seatDtoExpected.getId());
        seatExpected.setSeatNumber(seatDtoExpected.getSeatNumber());
        seatExpected.setSeatType(seatType); // TODO: must be changed or approved after DTO concept discussing.
        seatExpected.setAircraft(aircraft); // TODO: must be changed or approved after DTO concept discussing.
    }

    @Test
    void toDto() {
        // given
        // when
        SeatDto seatDto1 = seatMapper.toDto(seatExpected);
        // then
        assertThat(seatDto1).isEqualTo(seatDtoExpected);
    }


//    TODO: Обсудить, что мы должны тестировать и как это делать.
    @Test
    @Transactional
    void toModel() {
        Seat seat1 = seatMapper.toModel(seatDtoExpected);
        assertThat(seat1).isEqualTo(seatExpected);
    }
}
