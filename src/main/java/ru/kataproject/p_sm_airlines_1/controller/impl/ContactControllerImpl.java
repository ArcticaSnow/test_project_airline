package ru.kataproject.p_sm_airlines_1.controller.impl;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.kataproject.p_sm_airlines_1.controller.ContactController;
import ru.kataproject.p_sm_airlines_1.entity.Dto.ContactDto;
import ru.kataproject.p_sm_airlines_1.service.ContactService;
import ru.kataproject.p_sm_airlines_1.util.mapper.mapStruct.ContactMapper;

import java.util.List;

/**
 * Class ContactControllerImpl.
 * Implements ContactController interface.
 *
 * @author Ekaterina Kuchmistova (katy.shamina@yandex.ru)
 * @since 28.11.2022
 */
@Log4j2
@RestController
@SecurityRequirement(name = "swagger_auth")
@RequiredArgsConstructor
public class ContactControllerImpl implements ContactController {
    /**
     * Contact Service
     */
    private final ContactService contactService;
    /**
     * Contact Mapper
     */
    private final ContactMapper contactMapper;

    /**
     * Method gets all Contacts
     *
     * @return All Contacts
     */
    @Override
    @ResponseStatus(HttpStatus.OK)
    public List<ContactDto> getAllContacts() {
        log.info("execute getAllContacts method");
        return contactService.getAllContacts();
    }

    /**
     * Method gets Contact by id
     *
     * @param id Id
     * @return ContactDto
     */
    @Override
    @ResponseStatus(HttpStatus.OK)
    public ContactDto getContactById(@PathVariable Long id) {
        log.info("execute getContactById method");
        return contactMapper.toDto(contactService.getContactById(id));
    }

    /**
     * Method creates Contact
     *
     * @param contactDto ContactDto
     */
    @Override
    @ResponseStatus(HttpStatus.CREATED)
    public void createContact(ContactDto contactDto) {
        log.info("execute createContact method");
        contactService.createContact(contactMapper.toModel(contactDto));
    }

    /**
     * Method updates Contact
     *
     * @param contactDto ContactDto
     */
    @Override
    @ResponseStatus(HttpStatus.OK)
    public void updateContact(ContactDto contactDto) {
        log.info("execute updateContact method");
        contactService.updateContact(contactMapper.toModel(contactDto));
    }

    /**
     * Method deletes Contact by id
     *
     * @param id Long
     */
    @Override
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContact(Long id) {
        log.info("execute deleteContact method");
        contactService.deleteContact(id);
    }
}
