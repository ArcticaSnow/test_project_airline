package ru.kataproject.p_sm_airlines_1.util.mapper.mapStruct;

import org.mapstruct.Mapper;
import ru.kataproject.p_sm_airlines_1.entity.Contact;
import ru.kataproject.p_sm_airlines_1.entity.Dto.ContactDto;

/**
 * Interface ContactMapper.
 * Declares Contact mapper between Contact and ContactDto via MapStruct.
 *
 * @author Ekaterina Kuchmistova (katy.shamina@yandex.ru)
 * @since 28.11.2022
 */
@Mapper(componentModel = "spring", uses = ContactMapper.class)
public interface ContactMapper {
    /**
     * Method maps ContactDto to Contact
     *
     * @param contactDto ContactDto
     * @return Contact
     */
    Contact toModel(ContactDto contactDto);

    /**
     * Method Contact to ContactDto
     *
     * @param contact Contact
     * @return ContactDto
     */
    ContactDto toDto(Contact contact);
}
