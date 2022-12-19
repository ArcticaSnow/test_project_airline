package ru.kataproject.p_sm_airlines_1.service;

import ru.kataproject.p_sm_airlines_1.entity.Dto.DocumentDto;

import java.util.List;

/**
 * Interface Document.
 * Declares Document Service API.
 *
 * @author Mishin Yura (mishin.inbox@gmail.com)
 * @since 06.10.2022
 */
public interface DocumentService {
    /**
     * Method gets all documents.
     *
     * @return All Documents
     */
    List<DocumentDto> getAllDocuments();

    /**
     * Method gets document by id.
     *
     * @param id Id
     * @return Document
     */
    DocumentDto getDocumentById(Long id);

    /**
     * Method creates document.
     *
     * @param documentDto DocumentDto
     */
    void createDocument(DocumentDto documentDto);

    /**
     * Method updates document.
     *
     * @param documentDto DocumentDto
     */
    void updateDocument(DocumentDto documentDto);

    /**
     * Method deletes document by id.
     *
     * @param id Id
     */
    void deleteDocumentById(Long id);
}
