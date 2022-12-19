package ru.kataproject.p_sm_airlines_1.controller;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import ru.kataproject.p_sm_airlines_1.entity.Passenger;
import ru.kataproject.p_sm_airlines_1.repository.PassengerRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@Disabled("test doesn't work")
@DirtiesContext
@Testcontainers
@AutoConfigureMockMvc
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@AutoConfigureWebTestClient(timeout = "30s")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class EmailControllerTest {
    @Container
    private static final PostgreSQLContainer<?> postgreSQLContainer =
            new PostgreSQLContainer<>("postgres:13.2");

    @DynamicPropertySource
    static void initProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.liquibase.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.liquibase.password", postgreSQLContainer::getPassword);
        registry.add("spring.liquibase.user", postgreSQLContainer::getUsername);
    }

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private PassengerRepository passengerRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @Data
    private static class Auth {
        String username; // "admin",
        String password; // "admin"
    }

    private final static String AUTH_URL = "/v1/authenticate";
    private static String accessToken = null;

    /**
     * You should provide real receiver email address to tests.
     */
    private static final String email = "";

    private static final Random r = new Random();
    private static List<Passenger> passengers = null;
    private static Passenger passengerWithInvalidEmail = null;
    private static Passenger passengerWithUnknownEmail = null;

    @BeforeAll
    static void init() {
        assertThat(email).isNotBlank();
    }

    @BeforeEach
    // @Transactional
    void setUp() throws JsonProcessingException {
        if (passengers == null) {
            passengers = new ArrayList<>();
            // valid addresses
            Passenger passenger = new Passenger();
            passenger.setUsername(email);
            passenger.setFirstName("123");
            passenger.setLastName("123");
            passenger.setDateOfBirth(LocalDate.EPOCH);
            passengerRepository.saveAndFlush(passenger);
            passengers.add(passenger);

            passenger = new Passenger();
            passenger.setUsername(email);
            passenger.setFirstName("456");
            passenger.setLastName("456");
            passenger.setDateOfBirth(LocalDate.EPOCH);
            passengerRepository.saveAndFlush(passenger);
            passengers.add(passenger);

            passenger = new Passenger();
            passenger.setUsername(email);
            passenger.setFirstName("789");
            passenger.setLastName("789");
            passenger.setDateOfBirth(LocalDate.EPOCH);
            passengerRepository.saveAndFlush(passenger);
            passengers.add(passenger);

            // unknown address
            passengerWithUnknownEmail = new Passenger();
            passengerWithUnknownEmail.setUsername("user." + r.nextInt(1000) + "@gmail.com");
            passengerWithUnknownEmail.setFirstName("012");
            passengerWithUnknownEmail.setLastName("012");
            passengerWithUnknownEmail.setDateOfBirth(LocalDate.EPOCH);
            passengerRepository.saveAndFlush(passengerWithUnknownEmail);

            // invalid address
            passengerWithInvalidEmail = new Passenger();
            passengerWithInvalidEmail.setUsername("user@gmail.com" + r.nextInt(1000));
            passengerWithInvalidEmail.setFirstName("098");
            passengerWithInvalidEmail.setLastName("098");
            passengerWithInvalidEmail.setDateOfBirth(LocalDate.EPOCH);
            passengerRepository.saveAndFlush(passengerWithInvalidEmail);

        }

        getAuthToken();
    }

    // Get keycloak auth token
    void getAuthToken() throws JsonProcessingException {
        if (accessToken == null) {
            Auth auth = new Auth();
            auth.setUsername("admin");
            auth.setPassword("admin");

            String response = webTestClient
                    .post()
                    .uri(AUTH_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(fromValue(objectMapper.writeValueAsString(auth)))
                    .exchange()
                    .expectBody(String.class)
                    .returnResult()
                    .getResponseBody();

            DocumentContext jsonContext = JsonPath.parse(response);
            accessToken = jsonContext.read("$['access_token']");
            log.info("accessToken=" + accessToken);
        }
    }

    @Test
    void sendEmail() {
        // given
        String subject = "email subject, тема письма";
        String body = "sent via controller, отправлено через контроллер ";
        String attachment = "classpath:email/attachment.txt";

        String url = String.format("%s/passenger/%d?subject=%s&attachment=%s",
                EmailController.BASE_NAME,
                passengers.get(0).getId(),
                subject,
                attachment
        );

        // when
        webTestClient
                .post()
                .uri(url)
                .contentType(MediaType.TEXT_PLAIN)
                .headers(http -> http.setBearerAuth(accessToken))
                .body(fromValue(body))
                .exchange()
                .expectStatus().isEqualTo(200);

        // then
        // TODO getting email from imap service not implemented jet
    }

    @Test
    void sendEmailToMayPassengersById() {
        // given
        String subject = "email subject to multiple passengers, тема письма для нескольких пассажиров";
        String body = "sent via controller to several passengers, отправлено через контролллер нескольким пассажирам";
        String ids = "";
        for (Passenger passenger: passengers) {
            ids = ids + "&id=" + passenger.getId();
            passenger = passengerRepository.getById(passenger.getId());
            log.debug("sendEmails: added" + passenger);
        }
        String url = String.format("%s/passengers?%s&subject=%s",
                EmailController.BASE_NAME,
                ids,
                subject
        );
        log.info("url = " + url);

        // when
        webTestClient
                .post()
                .uri(url)
                .contentType(MediaType.TEXT_PLAIN)
                .headers(http -> http.setBearerAuth(accessToken))
                .body(fromValue(body))
                .exchange()
                .expectStatus().isEqualTo(200);

        // then
        // TODO getting email from imap service not implemented jet
    }

    @Test
    void sendSimpleEmail() {
        // given
        String subject = "sendSimpleEmail проверка";
        String body = "sendSimpleEmail test, sendSimpleEmail проверка";

        String url = String.format("%s/to/%s?subject=%s",
                EmailController.BASE_NAME,
                email,
                subject);

        // when
        webTestClient
                .post()
                .uri(url)
                .contentType(MediaType.TEXT_PLAIN)
                .headers(http -> http.setBearerAuth(accessToken))
                .body(fromValue(body))
                .exchange()
                .expectStatus().isEqualTo(200);

        // then
        // TODO getting email from imap service not implemented jet
    }
}