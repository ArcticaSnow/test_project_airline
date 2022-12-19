package ru.kataproject.p_sm_airlines_1.entity.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import ru.kataproject.p_sm_airlines_1.entity.ContactType;

/**
 * Class ContactDto.
 * Implements Contact Response Dto.
 *
 * @author Ekaterina Kuchmistova (katy.shamina@yandex.ru)
 * @since 25.11.2022
 */
@Value
@EqualsAndHashCode
@ToString
public class ContactDto {
    /**
     * Id.
     */
    @Schema(description = "Contact id", example = "1", type = "Long")
    Long id;

    /**
     * Contact Type.
     */
    @Schema(description = "Contact type", example = "Phone number", type = "Enum")
    ContactType type;

    /**
     * Contact Value.
     */
    @Schema(description = "Contact value", example = "+78009999999", type = "String")
    String value;

    /**
     * Preferred Contact.
     */
    @Schema(description = "Preferred Contact", example = "true", type = "Boolean")
    Boolean preferredContact;

}
