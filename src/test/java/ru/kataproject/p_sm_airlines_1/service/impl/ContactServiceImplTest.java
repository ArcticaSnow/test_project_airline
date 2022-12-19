package ru.kataproject.p_sm_airlines_1.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.kataproject.p_sm_airlines_1.entity.Contact;
import ru.kataproject.p_sm_airlines_1.entity.ContactType;
import ru.kataproject.p_sm_airlines_1.entity.Dto.ContactDto;
import ru.kataproject.p_sm_airlines_1.repository.ContactRepository;
import ru.kataproject.p_sm_airlines_1.service.ContactService;
import ru.kataproject.p_sm_airlines_1.util.exceptions.ContactNotFoundException;
import ru.kataproject.p_sm_airlines_1.util.mapper.mapStruct.ContactMapper;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * ContactService test class.
 *
 * @author Ekaterina Kuchmistova (katy.shamina@yandex.ru)
 * @since 30.11.2022
 */
@SpringBootTest
@Slf4j
class ContactServiceImplTest {
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private ContactService contactService;
    @Autowired
    private ContactMapper contactMapper;

    private final Random r = new Random();
    private Contact contactExpected;

    @BeforeEach
    void setUp() {
        contactExpected = new Contact();
        contactExpected.setType(ContactType.EMAIL);
        contactExpected.setValue(r.nextInt(1000) + "test@mail.ru");
        contactExpected.setPreferredContact(true);
    }

    @Test
    void shouldGetAllContacts() {
        Contact contactExpected2 = new Contact();
        contactExpected2.setType(ContactType.EMAIL);
        contactExpected2.setValue(r.nextInt(1000) + "test@mail.ru");
        contactExpected2.setPreferredContact(true);

        contactService.createContact(contactExpected);
        contactService.createContact(contactExpected2);

        List<ContactDto> list = contactService.getAllContacts();

        assertTrue(list.contains(contactMapper.toDto(contactExpected)));
        assertTrue(list.contains(contactMapper.toDto(contactExpected2)));
    }

    @Test
    @Transactional
    void shouldCreateContact() {
        contactService.createContact(contactExpected);
        Contact contactActual = contactRepository.getById(contactExpected.getId());
        assertThat(contactActual).isEqualTo(contactExpected);
    }

    @Test
    @Transactional
    void shouldUpdateContact() {
        log.info("updateContact <-");

        contactService.createContact(contactExpected);

        contactExpected.setType(ContactType.PHONE);
        contactExpected.setValue("+780099" + r.nextInt(1000));
        log.info("updateContact...");

        log.info("getById...");
        Contact contactActual = contactRepository.getById(contactExpected.getId());
        assertThat(contactActual).isEqualTo(contactExpected);
    }

    @Test
    void shouldGetContactById() {
        contactService.createContact(contactExpected);

        Contact contactActual = contactService.getContactById(contactExpected.getId());
        assertThat(contactActual).isEqualTo(contactExpected);
    }

    @Test
    void shouldDeleteContact() {
        contactService.createContact(contactExpected);

        contactService.deleteContact(contactExpected.getId());
        try {
            contactService.getContactById(contactExpected.getId());
        } catch (ContactNotFoundException ex) {
            return;
        }

        fail("contactNotFoundException must be thrown");
    }

}