package ru.kataproject.p_sm_airlines_1.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.kataproject.p_sm_airlines_1.entity.Document;
import ru.kataproject.p_sm_airlines_1.entity.DocumentType;
import ru.kataproject.p_sm_airlines_1.entity.Dto.DocumentDto;
import ru.kataproject.p_sm_airlines_1.entity.Passenger;
import ru.kataproject.p_sm_airlines_1.repository.DocumentRepository;
import ru.kataproject.p_sm_airlines_1.util.exceptions.DocumentNotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {DocumentServiceImpl.class})
@ExtendWith(SpringExtension.class)
@Slf4j
class DocumentServiceTest {
    @MockBean
    private DocumentRepository documentRepository;

    @Autowired
    private DocumentServiceImpl documentServiceImpl;

    private Passenger passenger;

    @BeforeEach
    void setUp() {
        passenger = new Passenger();
        passenger.setLastName("firstName");
        passenger.setLastName("lastName");
        passenger.setUsername("user@domen.com");
        passenger.setDocuments(new LinkedList<>());
    }

    /**
     * Method under test: {@link DocumentServiceImpl#getAllDocuments()}
     */
    @Test
    void testGetAllDocuments() {
        when(documentRepository.findAll((Sort) any()))
                .thenReturn(new ArrayList<>());

        assertTrue(documentServiceImpl.getAllDocuments().isEmpty());

        verify(documentRepository).findAll((Sort) any());
    }

    /**
     * Method under test: {@link DocumentServiceImpl#getAllDocuments()}
     */
    @Test
    void testGetAllDocuments2() {
        when(documentRepository.findAll((Sort) any()))
                .thenThrow(new DocumentNotFoundException());

        assertThrows(DocumentNotFoundException.class, () -> documentServiceImpl.getAllDocuments());

        verify(documentRepository).findAll((Sort) any());
    }

    /**
     * Method under test: {@link DocumentServiceImpl#getDocumentById(Long)}
     */
    @Test
    void testGetDocumentById() {
        // given
        Document document = new Document();
        document.setExpiryDate(LocalDate.ofEpochDay(1L));
        document.setId(123L);
        document.setNumber("42");
        // document.setPassengerId(123L);
        document.setType(DocumentType.NATIONAL_PASSPORT);

        document.setPassenger(passenger);
        passenger.getDocuments().add(document);

        Optional<Document> ofResult = Optional.of(document);

        // when
            // mock
        when(documentRepository.findById(any())).thenReturn(ofResult);

        DocumentDto actualDocumentById = documentServiceImpl.getDocumentById(123L);

        // then
        assertEquals("1970-01-02", actualDocumentById.getExpiryDate().toString());
        assertEquals(DocumentType.NATIONAL_PASSPORT, actualDocumentById.getType());
        // assertEquals("user@domen.com", actualDocumentById.getPassenger().getUsername());
        assertEquals("42", actualDocumentById.getNumber());
        assertEquals(123L, actualDocumentById.getId().longValue());

        verify(documentRepository).findById(any());
    }

    /**
     * Method under test: {@link DocumentServiceImpl#getDocumentById(Long)}
     */
    @Test
    void testGetDocumentById2() {
        Document document = new Document();
        document.setExpiryDate(LocalDate.ofEpochDay(1L));
        document.setId(123L);
        document.setNumber("42");
        document.setType(DocumentType.NATIONAL_PASSPORT);
        document.setPassenger(passenger);
        passenger.getDocuments().add(document);

        Document document1 = new Document();
        document1.setExpiryDate(LocalDate.ofEpochDay(1L));
        document1.setId(123L);
        document1.setNumber("42");
        document1.setType(DocumentType.NATIONAL_PASSPORT);
        document1.setPassenger(passenger);
        passenger.getDocuments().add(document1);

        Document document2 = new Document();
        document2.setExpiryDate(LocalDate.ofEpochDay(1L));
        document2.setId(123L);
        document2.setNumber("42");
        document2.setType(DocumentType.NATIONAL_PASSPORT);
        document2.setPassenger(passenger);
        passenger.getDocuments().add(document2);
        document2.setPassenger(passenger);
        passenger.getDocuments().add(document2);

        Document document3 = new Document();
        document3.setExpiryDate(LocalDate.ofEpochDay(1L));
        document3.setId(123L);
        document3.setNumber("42");
        document3.setType(DocumentType.NATIONAL_PASSPORT);
        document3.setPassenger(passenger);
        passenger.getDocuments().add(document3);

        Document document4 = new Document();
        document4.setExpiryDate(LocalDate.ofEpochDay(1L));
        document4.setId(123L);
        document4.setNumber("42");
        document4.setType(DocumentType.NATIONAL_PASSPORT);
        document4.setPassenger(passenger);
        passenger.getDocuments().add(document4);

        // mockito
        Document document5 = mock(Document.class);
        when(document5.getId()).thenReturn(123L);
        when(document5.getPassenger()).thenReturn(passenger);
        when(document5.getNumber()).thenReturn("42");
        when(document5.getExpiryDate()).thenReturn(LocalDate.ofEpochDay(1L));

        when(document5.setExpiryDate(any())).thenReturn(document);
        when(document5.setId(any())).thenReturn(document1);
        when(document5.setNumber(any())).thenReturn(document2);
        when(document5.setPassenger(any())).thenReturn(document3);
        when(document5.setType(any())).thenReturn(document4);

        when(document5.getType()).thenReturn(DocumentType.NATIONAL_PASSPORT);

        document5.setExpiryDate(LocalDate.ofEpochDay(1L));
        document5.setId(123L);
        document5.setNumber("42");
        document5.setPassenger(passenger);
        document5.setType(DocumentType.NATIONAL_PASSPORT);

        Optional<Document> ofResult = Optional.of(document5);

        when(documentRepository.findById(any())).thenReturn(ofResult);

        DocumentDto actualDocumentById = documentServiceImpl.getDocumentById(123L);

        assertEquals("1970-01-02", actualDocumentById.getExpiryDate().toString());
        assertEquals(DocumentType.NATIONAL_PASSPORT, actualDocumentById.getType());
        // assertEquals("user@domen.com", actualDocumentById.getPassenger().getUsername());
        assertEquals("42", actualDocumentById.getNumber());
        assertEquals(123L, actualDocumentById.getId().longValue());

        verify(documentRepository).findById(any());
        verify(document5).getId();
        verify(document5).getPassenger();
        verify(document5).getNumber();
        verify(document5).getExpiryDate();
        verify(document5).setExpiryDate(any());
        verify(document5).setId(any());
        verify(document5).setNumber(any());
        verify(document5).setPassenger(any());
        verify(document5).setType(any());
        verify(document5).getType();
    }

    /**
     * Method under test: {@link DocumentServiceImpl#createDocument(DocumentDto)}
     */
    @Test
    void testCreateDocument() {
        Document document = new Document();
        document.setExpiryDate(LocalDate.ofEpochDay(1L));
        document.setId(123L);
        document.setNumber("42");
        document.setPassenger(passenger);
        document.setType(DocumentType.NATIONAL_PASSPORT);

        when(documentRepository.saveAndFlush(any())).thenReturn(document);

        documentServiceImpl
                .createDocument(new DocumentDto(123L, DocumentType.NATIONAL_PASSPORT, "42",
                          LocalDate.ofEpochDay(1L), passenger.getId()));

        verify(documentRepository).saveAndFlush(any());
    }

    /**
     * Method under test: {@link DocumentServiceImpl#createDocument(DocumentDto)}
     */
    @Test
    void testCreateDocument2() {
        when(documentRepository.saveAndFlush(any()))
                .thenThrow(new DocumentNotFoundException());

        assertThrows(DocumentNotFoundException.class, () -> documentServiceImpl
                .createDocument(new DocumentDto(123L, DocumentType.NATIONAL_PASSPORT, "42",
                        LocalDate.ofEpochDay(1L), passenger.getId())));

        verify(documentRepository).saveAndFlush(any());
    }

    /**
     * Method under test: {@link DocumentServiceImpl#updateDocument(DocumentDto)}
     */
    @Test
    void testUpdateDocument() {
        Document document = new Document();
        document.setExpiryDate(LocalDate.ofEpochDay(1L));
        document.setId(123L);
        document.setNumber("42");
        document.setPassenger(passenger);
        document.setType(DocumentType.NATIONAL_PASSPORT);
        Optional<Document> ofResult = Optional.of(document);
        passenger.getDocuments().add(document);

        Document document1 = new Document();
        document1.setExpiryDate(LocalDate.ofEpochDay(1L));
        document1.setId(123L);
        document1.setNumber("42");
        document1.setPassenger(passenger);
        document1.setType(DocumentType.NATIONAL_PASSPORT);
        passenger.getDocuments().add(document1);

        when(documentRepository.saveAndFlush(any())).thenReturn(document1);

        when(documentRepository.findById(any())).thenReturn(ofResult);

        documentServiceImpl
                .updateDocument(new DocumentDto(123L, DocumentType.NATIONAL_PASSPORT, "42",
                        LocalDate.ofEpochDay(1L), passenger.getId()));
        verify(documentRepository).saveAndFlush(any());
        verify(documentRepository).findById(any());
    }

    /**
     * Method under test: {@link DocumentServiceImpl#updateDocument(DocumentDto)}
     */
    @Test
    void testUpdateDocument2() {
        Document document = new Document();

        document.setExpiryDate(LocalDate.ofEpochDay(1L));
        document.setId(123L);
        document.setNumber("42");
        document.setPassenger(passenger);
        document.setType(DocumentType.NATIONAL_PASSPORT);
        passenger.getDocuments().add(document);

        Optional<Document> ofResult = Optional.of(document);

        when(documentRepository.saveAndFlush(any())).thenThrow(new DocumentNotFoundException());

        when(documentRepository.findById(any())).thenReturn(ofResult);

        assertThrows(DocumentNotFoundException.class, () -> documentServiceImpl
                .updateDocument(new DocumentDto(123L, DocumentType.NATIONAL_PASSPORT, "42",
                        LocalDate.ofEpochDay(1L), passenger.getId())));

        verify(documentRepository).saveAndFlush(any());

        verify(documentRepository).findById(any());
    }

    /**
     * Method under test: {@link DocumentServiceImpl#updateDocument(DocumentDto)}
     */
    @Test
    void testUpdateDocument3() {
        Document document = new Document();
        document.setExpiryDate(LocalDate.ofEpochDay(1L));
        document.setId(123L);
        document.setNumber("42");
        document.setPassenger(passenger);
        document.setType(DocumentType.NATIONAL_PASSPORT);
        passenger.getDocuments().add(document);

        Document document1 = new Document();
        document1.setExpiryDate(LocalDate.ofEpochDay(1L));
        document1.setId(123L);
        document1.setNumber("42");
        document1.setPassenger(passenger);
        document1.setType(DocumentType.NATIONAL_PASSPORT);
        passenger.getDocuments().add(document1);

        Document document2 = new Document();
        document2.setExpiryDate(LocalDate.ofEpochDay(1L));
        document2.setId(123L);
        document2.setNumber("42");
        document2.setPassenger(passenger);
        document2.setType(DocumentType.NATIONAL_PASSPORT);
        passenger.getDocuments().add(document2);

        Document document3 = new Document();
        document3.setExpiryDate(LocalDate.ofEpochDay(1L));
        document3.setId(123L);
        document3.setNumber("42");
        document3.setPassenger(passenger);
        document3.setType(DocumentType.NATIONAL_PASSPORT);
        passenger.getDocuments().add(document3);

        Document document4 = new Document();
        document4.setExpiryDate(LocalDate.ofEpochDay(1L));
        document4.setId(123L);
        document4.setNumber("42");
        document4.setPassenger(passenger);
        document4.setType(DocumentType.NATIONAL_PASSPORT);
        passenger.getDocuments().add(document4);

        Document document5 = mock(Document.class);

        when(document5.setExpiryDate(any())).thenReturn(document);
        when(document5.setId(any())).thenReturn(document1);
        when(document5.setNumber(any())).thenReturn(document2);
        when(document5.setPassenger(any())).thenReturn(document3);
        when(document5.setType(any())).thenReturn(document4);

        document5.setExpiryDate(LocalDate.ofEpochDay(1L));
        document5.setId(123L);
        document5.setNumber("42");
        document5.setPassenger(passenger);
        document5.setType(DocumentType.NATIONAL_PASSPORT);
        passenger.getDocuments().add(document5);

        Optional<Document> ofResult = Optional.of(document5);

        Document document6 = new Document();
        document6.setExpiryDate(LocalDate.ofEpochDay(1L));
        document6.setId(123L);
        document6.setNumber("42");
        document6.setPassenger(passenger);
        document6.setType(DocumentType.NATIONAL_PASSPORT);
        passenger.getDocuments().add(document6);

        when(documentRepository.saveAndFlush(any())).thenReturn(document6);

        when(documentRepository.findById(any())).thenReturn(ofResult);

        documentServiceImpl
                .updateDocument(new DocumentDto(123L, DocumentType.NATIONAL_PASSPORT, "42",
                        LocalDate.ofEpochDay(1L), passenger.getId()));

        verify(documentRepository).saveAndFlush(any());

        verify(documentRepository).findById(any());

        verify(document5, atLeast(1)).setExpiryDate(any());
        verify(document5, atLeast(1)).setId(any());
        verify(document5, atLeast(1)).setNumber(any());
        verify(document5, atLeast(1)).setPassenger(any());
        verify(document5, atLeast(1)).setType(any());
    }

    /**
     * Method under test: {@link DocumentServiceImpl#deleteDocumentById(Long)}
     */
    @Test
    void testDeleteDocumentById() {
        Document document = new Document();
        document.setExpiryDate(LocalDate.ofEpochDay(1L));
        document.setId(123L);
        document.setNumber("42");
        document.setPassenger(passenger);
        document.setType(DocumentType.NATIONAL_PASSPORT);

        Optional<Document> ofResult = Optional.of(document);

        doNothing().when(documentRepository).deleteById(any());

        when(documentRepository.findById(any())).thenReturn(ofResult);

        documentServiceImpl.deleteDocumentById(123L);

        verify(documentRepository).findById(any());
        verify(documentRepository).deleteById(any());
    }

    /**
     * Method under test: {@link DocumentServiceImpl#deleteDocumentById(Long)}
     */
    @Test
    void testDeleteDocumentById2() {
        Document document = new Document();
        document.setExpiryDate(LocalDate.ofEpochDay(1L));
        document.setId(123L);
        document.setNumber("42");
        document.setPassenger(passenger);
        document.setType(DocumentType.NATIONAL_PASSPORT);
        passenger.getDocuments().add(document);

        Optional<Document> ofResult = Optional.of(document);

        doThrow(new DocumentNotFoundException()).when(documentRepository).deleteById(any());

        when(documentRepository.findById(any())).thenReturn(ofResult);

        assertThrows(DocumentNotFoundException.class, () -> documentServiceImpl.deleteDocumentById(123L));

        verify(documentRepository).findById(any());
        verify(documentRepository).deleteById(any());
    }
}

