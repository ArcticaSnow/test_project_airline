package ru.kataproject.p_sm_airlines_1.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.kataproject.p_sm_airlines_1.entity.Document;
import ru.kataproject.p_sm_airlines_1.entity.DocumentType;
import ru.kataproject.p_sm_airlines_1.entity.Dto.PassengerDto;
import ru.kataproject.p_sm_airlines_1.entity.Passenger;
import ru.kataproject.p_sm_airlines_1.service.PassengerService;
import ru.kataproject.p_sm_airlines_1.util.exceptions.PassengerNotFoundException;
import ru.kataproject.p_sm_airlines_1.util.mapper.mapStruct.PassengerMapper;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * PassengerService tests class.
 */
@SpringBootTest
@Slf4j
@ContextConfiguration(initializers = {PassengerServiceTest.Initializer.class})
class PassengerServiceTest {

    /**
     * TestContainer configuration
     */
    private static final PostgreSQLContainer sqlContainer;

    static {
        sqlContainer = new PostgreSQLContainer("postgres:13.2")
                .withDatabaseName("airline_db")
                .withUsername("root")
                .withPassword("root");
        sqlContainer.start();
    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + sqlContainer.getJdbcUrl(),
                    "spring.datasource.username=" + sqlContainer.getUsername(),
                    "spring.datasource.password=" + sqlContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    /**
     * General stuff for test usage.
     */
    @Autowired
    private PassengerService passengerService;

    @Autowired
    private PassengerMapper passengerMapper;

    private final Random r = new Random();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    /**
     * Method under test: {@link PassengerServiceImpl#getAllPassengers()}
     */
    @Test
    void getAllPassengers() {
        // given
        List<Passenger> list1 = new LinkedList<>();

        for (int i = 0; i < 3; i++) {
            Passenger passenger = new Passenger();
            passenger.setUsername(String.format("user-%s@domen.ru", r.nextInt(1000)));

            Document document = new Document();
            document.setType(DocumentType.NATIONAL_PASSPORT);
            document.setNumber("" + r.nextInt(10000));
            // bind to each other
            document.setPassenger(passenger);
            passenger.getDocuments().add(document);

            passengerService.savePassenger(passenger);
            list1.add(passenger);
        }

        // when
        List<PassengerDto> list2 = passengerService.getAllPassengers();

        // then
        assertTrue(list2.containsAll(
                list1.stream().map(passengerMapper::toDto).collect(Collectors.toList()))
        );
    }

    @Test
    void getPassengerByRealId() {
        // given
        Passenger passenger = new Passenger();
        passenger.setUsername(String.format("user-%s@domen.ru", r.nextInt(1000)));
        passengerService.savePassenger(passenger);

        // when
        Passenger p2 = passengerService.getPassengerById(passenger.getId());

        // then
        assertThat(p2).isEqualTo(passenger);
    }

    @Test
    void updatePassenger() {
        // given
        Passenger passenger = new Passenger();
        passenger.setUsername(String.format("user-%s@domen.ru", r.nextInt(1000)));
        passengerService.savePassenger(passenger);

        Document document = new Document();
        document.setNumber(""+r.nextInt(1000));
        document.setType(DocumentType.NATIONAL_PASSPORT);

        passenger.setUsername("new-" + r.nextInt(1000));
        passenger.getDocuments().add(document);
        document.setPassenger(passenger);

        // when
        passengerService.updatePassenger(passenger);
        Passenger p2 = passengerService.getPassengerById(passenger.getId());

        // then
        assertThat(p2).isEqualTo(passenger);
    }

    @Test
    void savePassenger() {
        // given
        Passenger passenger = new Passenger();
        passenger.setUsername(String.format("user-%s@domen.ru", r.nextInt(1000)));

        Document document = new Document();
        document.setNumber(""+r.nextInt(1000));
        document.setType(DocumentType.NATIONAL_PASSPORT);
        passenger.getDocuments().add(document);
        document.setPassenger(passenger);

        // when
        passengerService.savePassenger(passenger);
        Passenger p2 = passengerService.getPassengerById(passenger.getId());

        // then
        assertThat(p2).isEqualTo(passenger);
    }

    @Test
    void deletePassengerById() {
        // given
        Passenger passenger = new Passenger();
        passenger.setUsername(String.format("user-%s@domen.ru", r.nextInt(1000)));
        passengerService.savePassenger(passenger);
        Passenger p2 = passengerService.getPassengerById(passenger.getId());

        // when
        passengerService.deletePassengerById(p2.getId());
        try {
            passengerService.getPassengerById(p2.getId());
        } catch (PassengerNotFoundException ex) {
            return;
        }

        // then
        fail("PassengerNotFoundException must be thrown");
    }
}