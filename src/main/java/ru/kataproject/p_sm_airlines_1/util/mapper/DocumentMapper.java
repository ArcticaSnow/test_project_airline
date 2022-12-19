package ru.kataproject.p_sm_airlines_1.util.mapper;

import ru.kataproject.p_sm_airlines_1.entity.Document;
import ru.kataproject.p_sm_airlines_1.entity.Dto.DocumentDto;

/**
 * Interface DocumentMapper.
 * Declares Document mapper between Document and DocumentDto.
 *
 * @author Mishin Yura (mishin.inbox@gmail.com)
 * @since 12.10.2022
 */
public interface DocumentMapper {
    /**
     * Method maps DocumentDto to Document.
     *
     * @param documentDto DocumentDto
     * @return Document
     */
    Document mapToDocument(DocumentDto documentDto);

    /**
     * Method maps Document to DocumentDto.
     *
     * @param document Document
     * @return DocumentDto
     */
    DocumentDto mapToDocumentDto(Document document);

    /**
     * Method maps DocumentDto to Document.
     *
     * @param source      DocumentDto
     * @param destination Document
     * @return Document
     */
    Document map(DocumentDto source, Document destination);
}
