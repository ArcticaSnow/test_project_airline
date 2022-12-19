package ru.kataproject.p_sm_airlines_1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kataproject.p_sm_airlines_1.entity.Dto.DocumentDto;
import ru.kataproject.p_sm_airlines_1.entity.Dto.ErrorResponseDto;

import java.util.List;

/**
 * Interface DocumentController.
 * Declares Document API methods.
 *
 * @author Mishin Yura (mishin.inbox@gmail.com)
 * @since 06.10.2022
 */
@Tag(name = "Document controller")
@RequestMapping(DocumentController.BASE_NAME)
public interface DocumentController {
    /**
     * Major API version.
     */
    String MAJOR_VERSION = "/v1";
    /**
     * Base API name.
     */
    String BASE_NAME = MAJOR_VERSION + "/documents";

    /**
     * Method gets all documents.
     *
     * @return All Documents
     */
    @GetMapping()
    @Operation(
            method = "GET",
            summary = "Get all documents",
            description = "Get all documents"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Documents successfully returned",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DocumentDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Data not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    List<DocumentDto> getAllDocuments();

    /**
     * Method gets document by id.
     *
     * @param id Id
     * @return DocumentDto
     */
    @GetMapping("{id}")
    @Operation(
            method = "GET",
            summary = "Get document by id",
            description = "Get document by id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Document successfully returned",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DocumentDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Data not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    DocumentDto getDocumentById(@PathVariable Long id);

    /**
     * Method creates document.
     *
     * @param documentDto DocumentDto
     */
    @PostMapping()
    @Operation(
            method = "POST",
            summary = "Create document",
            description = "Create document"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Document successfully created",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DocumentDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    void createDocument(@RequestBody DocumentDto documentDto);

    /**
     * Method updates document.
     *
     * @param documentDto DocumentDto
     */
    @PutMapping()
    @Operation(
            method = "PUT",
            summary = "Update document",
            description = "Update document"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Document successfully updated",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DocumentDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    void updateDocument(@RequestBody DocumentDto documentDto);

    /**
     * Method deletes document by id.
     *
     * @param id Id
     */
    @DeleteMapping("{id}")
    @Operation(
            method = "DELETE",
            summary = "Delete document by id",
            description = "Delete document by id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Document successfully deleted",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DocumentDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    void deleteDocumentById(@PathVariable Long id);
}
