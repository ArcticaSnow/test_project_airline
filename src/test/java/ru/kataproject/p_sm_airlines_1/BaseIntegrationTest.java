package ru.kataproject.p_sm_airlines_1;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;
import static ru.kataproject.p_sm_airlines_1.util.ResourceUtil.getResourceAsString;

@Transactional
@DirtiesContext
@Testcontainers
@AutoConfigureMockMvc
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@AutoConfigureWebTestClient(timeout = "30s")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseIntegrationTest {
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
    protected WebTestClient webTestClient;

    protected void getTest(String url, Resource expectedResponse, int httpCode) {
        getTest(url, expectedResponse, httpCode, null);
    }

    protected void getTest(String url, Resource expectedResponse, int httpCode, String token) {
        webTestClient
                .get()
                .uri(url)
                .header("Authorization", String.format("Bearer %s", token))
                .exchange()
                .expectStatus().isEqualTo(httpCode)
                .expectBody()
                .json(getResourceAsString(expectedResponse));
    }

    protected void postTest(String url, Resource expectedRequest, Resource expectedResponse, int httpCode) {
        postTest(url, expectedRequest, expectedResponse, httpCode, null);
    }

    protected void postTest(String url, Resource expectedRequest, Resource expectedResponse, int httpCode, String token) {
        webTestClient
                .post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", String.format("Bearer %s", token))
                // .headers(http -> http.setBearerAuth(token))
                .body(fromValue(getResourceAsString(expectedRequest)))
                .exchange()
                .expectStatus().isEqualTo(httpCode)
                .expectBody()
                .json(getResourceAsString(expectedResponse));
    }

    protected void patchTest(String url, Resource expectedRequest, Resource expectedResponse, int httpCode) {
        patchTest(url, expectedRequest, expectedResponse, httpCode, null);
    }

    protected void patchTest(String url, Resource expectedRequest, Resource expectedResponse, int httpCode, String token) {
        webTestClient
                .patch()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", String.format("Bearer %s", token))
                .body(fromValue(getResourceAsString(expectedRequest)))
                .exchange()
                .expectStatus().isEqualTo(httpCode)
                .expectBody()
                .json(getResourceAsString(expectedResponse));
    }

    protected void putTest(String url, Resource expectedRequest, Resource expectedResponse, int httpCode) {
        putTest(url, expectedRequest, expectedResponse, httpCode, null);
    }

    protected void putTest(String url, Resource expectedRequest, Resource expectedResponse, int httpCode, String token) {
        webTestClient
                .put()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", String.format("Bearer %s", token))
                .body(fromValue(getResourceAsString(expectedRequest)))
                .exchange()
                .expectStatus().isEqualTo(httpCode)
                .expectBody()
                .json(getResourceAsString(expectedResponse));
    }


    protected void deleteTest(String url, Resource expectedResponse, int httpCode) {
        deleteTest(url, expectedResponse, httpCode, null);
    }

    protected void deleteTest(String url, Resource expectedResponse, int httpCode, String token) {
        webTestClient
                .delete()
                .uri(url)
                .header("Authorization", String.format("Bearer %s", token))
                .exchange()
                .expectStatus().isEqualTo(httpCode)
                .expectBody()
                .json(getResourceAsString(expectedResponse));
    }

}