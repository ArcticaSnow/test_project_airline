package ru.kataproject.p_sm_airlines_1;

import org.assertj.core.api.Assert;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.kataproject.p_sm_airlines_1.controller.FlightController;

/**
 *Тут будут тесты для полётного контроллера
 *
 * (C)Toboe512
 */
@Disabled("Error 401, need authentication")
@SpringBootTest
@AutoConfigureMockMvc
public class FlightTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FlightController flightController;
    @Test
    void test() {
        assertThat(flightController).isNotNull();

    }

    @Test
    void getFlightById() throws Exception {
        this.mockMvc.perform(get(FlightController.BASE_NAME).param("id", "1"))
                .andDo(print())
                .andExpect(status().isOk());
    }


}
