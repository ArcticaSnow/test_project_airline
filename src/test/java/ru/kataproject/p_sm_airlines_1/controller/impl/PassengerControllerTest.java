package ru.kataproject.p_sm_airlines_1.controller.impl;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import ru.kataproject.p_sm_airlines_1.BaseIntegrationTest;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;
import static ru.kataproject.p_sm_airlines_1.util.ResourceUtil.getResourceAsString;

@DisplayName("Интеграционные тесты проверки PassengerController")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PassengerControllerTest extends BaseIntegrationTest {

    @Value("classpath:/api/passenger/response/get-all-passengers.json")
    private Resource getAllPassengersExpectedResponse;

    @Value("classpath:/api/passenger/response/get-passenger-by-id.json")
    private Resource getByIdPassengerExpectedResponse;

    @Value("classpath:/api/passenger/request/create-passenger.json")
    private Resource createPassengerExpectedRequest;

    @Value("classpath:/api/passenger/response/create-passenger.json")
    private Resource createPassengerExpectedResponse;

    @Value("classpath:/api/passenger/request/update-passenger.json")
    private Resource updatePassengerExpectedRequest;

    @Value("classpath:/api/passenger/response/update-passenger.json")
    private Resource updatePassengerExpectedResponse;

    private final static String BASE_URL = "/v1/passengers";
    private final static int TEST_PASSENGER_ID_1 = 1;
    private final static int TEST_PASSENGER_ID_2 = 2;

    private final static String AUTH_URL = "/v1/authenticate";
    private static String accessToken;

    @Test
    @Order(0)
    @DisplayName("Get keycloak auth token")
    void getAuthToken() {

        String response = webTestClient
                .post()
                .uri(AUTH_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromValue(getResourceAsString("classpath:/api/auth/auth.json")))
                .exchange()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();

        DocumentContext jsonContext = JsonPath.parse(response);
        accessToken = jsonContext.read("$['access_token']");
    }

    @Test
    @Order(2)
    @DisplayName("Получение списка пассажиров")
    void getAllTest() {
        getTest(BASE_URL, getAllPassengersExpectedResponse, 200, accessToken);
    }

    @Test
    @Order(3)
    @DisplayName("Получение пассажира по идентификатору")
    void getByIdTest() {
        getTest(BASE_URL + "/" + TEST_PASSENGER_ID_1, getByIdPassengerExpectedResponse, 200, accessToken);
    }

    @Test
    @Order(4)
    @DisplayName("Создание пассажира")
    void createTest() {
        postTest(BASE_URL, createPassengerExpectedRequest, createPassengerExpectedResponse, 201, accessToken);
    }

    @Test
    @Order(1)
    @DisplayName("Редактирование пассажира")
    void updateTest() {
        patchTest(BASE_URL, updatePassengerExpectedRequest, updatePassengerExpectedResponse,200, accessToken);
    }

    @Test
    @Order(5)
    @DisplayName("Удаление пассажира")
    void deleteTest() {
        deleteTest(BASE_URL + "/" + TEST_PASSENGER_ID_2, null, 204, accessToken);
    }
}
