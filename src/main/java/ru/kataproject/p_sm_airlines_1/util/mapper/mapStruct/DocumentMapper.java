package ru.kataproject.p_sm_airlines_1.util.mapper.mapStruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import ru.kataproject.p_sm_airlines_1.entity.Document;
import ru.kataproject.p_sm_airlines_1.entity.Dto.DocumentDto;
import ru.kataproject.p_sm_airlines_1.entity.Passenger;

/**
 * Interface DocumentMapper.
 * Declares Document mapper between Document and DocumentDto via MapStruct.
 *
 * @author Mishin Yura (mishin.inbox@gmail.com)
 * @since 19.10.2022
 */
@Mapper(componentModel = "spring", uses = DocumentMapper.class)
public interface DocumentMapper {
    /**
     * DocumentMapper instance.
     */
    DocumentMapper INSTANCE = Mappers.getMapper(DocumentMapper.class);

    /**
     * Method maps DocumentDto to Document.
     *
     * @param documentDto DocumentDto
     * @return Document
     */
    Document mapToDocument(DocumentDto documentDto);

    /**
     * Maps passenger id to Passenger
     * @param value passenger id
     * @return Passenger
     */
    default Passenger map(Long value) {
        Passenger passenger = new Passenger();
        passenger.setId(value);
        return passenger;
    }

    /**
     * Method maps Document to DocumentDto.
     *
     * @param document Document
     * @return DocumentDto
     */
    @Mapping(source = "document.passenger.id", target = "passenger")
    DocumentDto mapToDocumentDto(Document document);

    /**
     * Method updates Document from DocumentDto.
     *
     * @param documentDto DocumentDto
     * @param document    Document
     */
    void updateDocumentFromDto(DocumentDto documentDto, @MappingTarget Document document);
}
