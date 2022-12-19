package ru.kataproject.p_sm_airlines_1.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kataproject.p_sm_airlines_1.entity.Contact;
import ru.kataproject.p_sm_airlines_1.entity.Dto.ContactDto;
import ru.kataproject.p_sm_airlines_1.repository.ContactRepository;
import ru.kataproject.p_sm_airlines_1.service.ContactService;
import ru.kataproject.p_sm_airlines_1.util.exceptions.ContactNotFoundException;
import ru.kataproject.p_sm_airlines_1.util.mapper.mapStruct.ContactMapper;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Class ContactServiceImpl.
 * Implements ContactService interface.
 *
 * @author Ekaterina Kuchmistova (katy.shamina@yandex.ru)
 * @since 25.11.2022
 */
@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
    /**
     * Contact Repository.
     */
    private final ContactRepository contactRepository;

    /**
     * Contact Mapper.
     */
    private final ContactMapper contactMapper;

    /**
     * Method gets all contacts.
     */
    @Override
    public List<ContactDto> getAllContacts() {
        return contactRepository.findAll()
                .stream()
                .map(contactMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Method gets contact by id.
     *
     * @param id Id
     */
    @Override
    public Contact getContactById(Long id) {
        Optional<Contact> contact = contactRepository.findById(id);
        return contact.orElseThrow(ContactNotFoundException::new);
    }

    /**
     * Method creates contact.
     *
     * @param contact Contact
     */
    @Transactional
    @Override
    public void createContact(Contact contact) {
        contactRepository.save(contact);
    }

    /**
     * Method updates contact.
     *
     * @param contact Contact
     */
    @Transactional
    @Override
    public void updateContact(Contact contact) {
        contactRepository.save(contact);
    }

    /**
     * Method deletes contact by id.
     *
     * @param id Id
     */
    @Transactional
    @Override
    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }
}
