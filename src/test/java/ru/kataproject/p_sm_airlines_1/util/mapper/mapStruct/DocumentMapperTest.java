package ru.kataproject.p_sm_airlines_1.util.mapper.mapStruct;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.kataproject.p_sm_airlines_1.entity.Document;
import ru.kataproject.p_sm_airlines_1.entity.DocumentType;
import ru.kataproject.p_sm_airlines_1.entity.Dto.DocumentDto;
import ru.kataproject.p_sm_airlines_1.entity.Passenger;
import ru.kataproject.p_sm_airlines_1.repository.DocumentRepository;
import ru.kataproject.p_sm_airlines_1.repository.PassengerRepository;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Class DocumentMapperTest.
 * Tests ru.kataproject.p_sm_airlines_1.util.mapper.mapStruct.DocumentMapper class.
 *
 * @author Mishin Yura (mishin.inbox@gmail.com)
 * @author Alexey Sen (alexey.sen@gmail.com)
 * @since 19.10.2022
 */
@SpringBootTest
@DisplayName("Tests DocumentMapper.class")
class DocumentMapperTest {
    private static DocumentDto documentDtoExpected;
    private static Document documentExpected;

    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private PassengerRepository passengerRepository;

    @BeforeEach
    void setUp() {
        Passenger passenger = new Passenger();
        passenger.setLastName("firstName");
        passenger.setLastName("lastName");
        passenger.setUsername("user@domen.com");

        documentExpected = new Document()
                .setType(DocumentType.NATIONAL_PASSPORT)
                .setNumber("A1B2")
                .setExpiryDate(LocalDate.parse("2030-09-09"))
                .setPassenger(passenger);

        passenger.getDocuments().add(documentExpected);

        passenger = passengerRepository.save(passenger);

        documentDtoExpected = new DocumentDto(
                documentExpected.getId(),
                documentExpected.getType(),
                documentExpected.getNumber(),
                documentExpected.getExpiryDate(),
                passenger.getId());
    }

    /**
     * Tests mapToDocument() method.
     */
    @DisplayName("tests mapToDocument() method")
    @Test
    void shouldMapToDocument() {
        // given

        // when
        var documentActual = DocumentMapper.INSTANCE.mapToDocument(documentDtoExpected);

        // then
        assertThat(documentActual)
                .isNotNull()
                .isInstanceOf(Document.class);

        assertThat(documentActual.getId()).isEqualTo(documentDtoExpected.getId());
        assertThat(documentActual.getNumber()).isEqualTo(documentExpected.getNumber());
        assertThat(documentActual.getType()).isEqualTo(documentExpected.getType());
        assertThat(documentActual.getExpiryDate()).isEqualTo(documentExpected.getExpiryDate());
        assertThat(documentActual.getPassenger().getId()).isEqualTo(documentExpected.getPassenger().getId());
    }

    /**
     * Tests mapToDocumentDto() method.
     */
    @DisplayName("tests mapToDocumentDto() method")
    @Test
    void shouldMapToDocumentDto() {
        // given

        // when
        var documentActual = DocumentMapper.INSTANCE.mapToDocumentDto(documentExpected);

        // then
        assertThat(documentActual).isEqualTo(documentDtoExpected);
    }

    /**
     * Tests updateDocumentFromDto() method.
     */
    @DisplayName("tests updateDocumentFromDto() method")
    @Test
    void shouldUpdateDocumentFromDto() {
        Passenger passenger1 = new Passenger();
        passenger1.setLastName("firstName1");
        passenger1.setLastName("lastName1");
        passenger1.setUsername("user@domen.com");

        Passenger passenger2 = new Passenger();
        passenger2.setLastName("firstName2");
        passenger2.setLastName("lastName2");
        passenger2.setUsername("user2@domen.com");

        // given
        var document = new Document()
                .setId(1L)
                .setType(DocumentType.NATIONAL_PASSPORT)
                .setNumber("Old")
                .setExpiryDate(LocalDate.parse("2030-09-09"))
                .setPassenger(passenger1);

        var documentDto = new DocumentDto(
                document.getId(),
                DocumentType.INTERNATIONAL_PASSPORT,
                "New",
                LocalDate.parse("2031-09-09"),
                passenger2.getId());

        // when
        DocumentMapper.INSTANCE.updateDocumentFromDto(documentDto, document);

        // then
        assertThat(document)
                .hasFieldOrPropertyWithValue("id", documentDto.getId())
                .hasFieldOrPropertyWithValue("type", documentDto.getType())
                .hasFieldOrPropertyWithValue("number", documentDto.getNumber())
                .hasFieldOrPropertyWithValue("expiryDate", documentDto.getExpiryDate());
        assertThat(document.getPassenger().getId()).isEqualTo(documentDto.getPassenger());
    }
}
