package ru.kataproject.p_sm_airlines_1.util.mapper.mapStruct;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.kataproject.p_sm_airlines_1.entity.Contact;
import ru.kataproject.p_sm_airlines_1.entity.ContactType;
import ru.kataproject.p_sm_airlines_1.entity.Dto.ContactDto;
import ru.kataproject.p_sm_airlines_1.repository.ContactRepository;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * ContactMapper test class.
 *
 * @author Ekaterina Kuchmistova (katy.shamina@yandex.ru)
 * @since 28.11.2022
 */
@SpringBootTest
class ContactMapperTest {
    @Autowired
    private ContactMapper contactMapper;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private static ContactDto contactDtoExpected;
    @Autowired
    private static Contact contactExpected;
    private final Random r = new Random();

    @BeforeEach
    void setUp() {
        contactExpected = new Contact();
        contactExpected.setType(ContactType.EMAIL);
        contactExpected.setValue(r.nextInt(1000) + "test@mail.ru");
        contactExpected.setPreferredContact(true);

        contactRepository.saveAndFlush(contactExpected);

        contactDtoExpected = new ContactDto(contactExpected.getId(),
                contactExpected.getType(),
                contactExpected.getValue(),
                contactExpected.getPreferredContact());
    }

    @Test
    void shouldMapToModel() {
        // given
        // when
        Contact contactActual = contactMapper.toModel(contactDtoExpected);
        // then
        assertThat(contactActual).isEqualTo(contactExpected);
    }

    @Test
    void shouldMapToDto() {
        // given
        // when
        ContactDto contactDtoActual = contactMapper.toDto(contactExpected);
        // then
        assertThat(contactDtoActual).isEqualTo(contactDtoExpected);
    }


}