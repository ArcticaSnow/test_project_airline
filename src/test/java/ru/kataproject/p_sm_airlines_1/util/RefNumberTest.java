package ru.kataproject.p_sm_airlines_1.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.kataproject.p_sm_airlines_1.service.BookingRefService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RefNumberTest {

    @Autowired
    private BookingRefService bookingRefService;

    @Test
    void shouldReturnRefNumber() {
        // given

        // when
        String refNumber = RefNumber.generate();
        assertThat(refNumber).isNotBlank();
    }
}