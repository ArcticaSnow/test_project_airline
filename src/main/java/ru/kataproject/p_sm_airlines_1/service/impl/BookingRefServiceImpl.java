package ru.kataproject.p_sm_airlines_1.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kataproject.p_sm_airlines_1.entity.BookingRef;
import ru.kataproject.p_sm_airlines_1.repository.BookingRefRepository;
import ru.kataproject.p_sm_airlines_1.service.BookingRefService;

import java.util.Base64;
import java.util.Random;

/**
 * BookingRef service implementation class.
 *
 * @author Alexey Sen (alexey.sen@gmail.com)
 * @since 31.10.2022
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class BookingRefServiceImpl implements BookingRefService {
    /**
     * Maximum number of attempts to generate refNumber in case of duplicate entries.
     */
    private static final int MAX_RETRY_COUNT = 3;

    /**
     * Booking Repository.
     */
    private final BookingRefRepository bookingRefRepository;

    @Transactional
    @Override
    public String createRefNumber() {
        BookingRef bookingRef = new BookingRef();
        bookingRef.setRefNumber(generateRefNumber());
        bookingRef = bookingRefRepository.saveAndFlush(bookingRef);
        return bookingRef.getRefNumber();
    }

    /*
     * Make sure that refNumber is unique.
     */
    private String generateRefNumber() {
        String refNumber = makeRefNumber();
        boolean isDuplicate = false;
        for (int i = 1; i < MAX_RETRY_COUNT; i++) {
            BookingRef b = bookingRefRepository.findByRefNumber(refNumber);
            if (b != null) {
                isDuplicate = true;
                // try another
                log.warn(String.format("Generated duplicate refNumber='%s'. Retry.", refNumber));
                refNumber = makeRefNumber();
            } else {
                isDuplicate = false;
                break;
            }
        }
        if (isDuplicate) {
            throw new RuntimeException("Booking refNumber generation is failed.");
        }
        return refNumber;
    }

    /*
     * Generates refNumber (just random in Base64).
     */
    private String makeRefNumber() {
        byte[] arr = new byte[8];
        Random r = new Random(System.currentTimeMillis());
        r.nextBytes(arr);
        String msg = Base64.getEncoder().encodeToString(arr)
                .toUpperCase().substring(0, arr.length)
                .replace('+', 'A')
                .replace('/', 'B');
        return msg;
    }
}

