package ru.kataproject.p_sm_airlines_1.service;

import ru.kataproject.p_sm_airlines_1.entity.Contact;
import ru.kataproject.p_sm_airlines_1.entity.Dto.ContactDto;

import java.util.List;

/**
 * Interface Document.
 * Declares Document Service API.
 *
 * @author Ekaterina Kuchmistova (katy.shamina@yandex.ru)
 * @since 25.11.2022
 */
public interface ContactService {
    /**
     * Method gets all contacts.
     */
    List<ContactDto> getAllContacts();

    /**
     * Method gets contact by id.
     *
     * @param id Id
     */
    Contact getContactById(Long id);

    /**
     * Method creates contact.
     *
     * @param contact Contact
     */
    void createContact(Contact contact);

    /**
     * Method updates contact.
     *
     * @param contact Contact
     */
    void updateContact(Contact contact);

    /**
     * Method deletes contact by id.
     *
     * @param id Id
     */
    void deleteContact(Long id);
}
