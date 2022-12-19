package ru.kataproject.p_sm_airlines_1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.kataproject.p_sm_airlines_1.entity.Dto.ContactDto;
import ru.kataproject.p_sm_airlines_1.entity.Dto.ErrorResponseDto;

import java.util.List;

/**
 * Interface ContactController.
 * Declares Contact API methods.
 *
 * @author Ekaterina Kuchmistova (katy.shamina@yandex.ru)
 * @since 25.11.2022
 */
@Tag(name = "Contact controller")
@RequestMapping(ContactController.BASE_NAME)
public interface ContactController {
    /**
     * Major API version.
     */
    String MAJOR_VERSION = "/v1";

    /**
     * Base API name.
     */
    String BASE_NAME = MAJOR_VERSION + "/contacts";

    /**
     * This method returns all Contacts.
     */
    @GetMapping()
    @Operation(summary = "Getting all Contacts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contacts successfully returned", content = @Content),
            @ApiResponse(responseCode = "400", description = "Data not found", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDto.class)
            ))
    })
    List<ContactDto> getAllContacts();

    /**
     * This method returns Contact by id.
     *
     * @param id Long
     */
    @GetMapping("/{id}")
    @Operation(summary = "Getting Contact by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contact successfully returned", content = @Content),
            @ApiResponse(responseCode = "400", description = "Data not found", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDto.class)
            ))
    })
    ContactDto getContactById(@PathVariable Long id);

    /**
     * This method creates a new Contact.
     *
     * @param contactDto ContactDto
     */
    @PostMapping
    @Operation(summary = "Create new contact")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Contact successfully created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDto.class)
            ))
    })
    void createContact(@RequestBody ContactDto contactDto);

    /**
     * This method creates a new Contact.
     *
     * @param contactDto ContactDto
     */
    @PutMapping
    @Operation(summary = "Update contact")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contact successfully updated", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDto.class)
            ))
    })
    void updateContact(@RequestBody ContactDto contactDto);

    /**
     * This method deletes the Contact by id.
     *
     * @param id Long
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Contact by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Contact successfully deleted", content = @Content),
            @ApiResponse(responseCode = "400", description = "Data not found", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDto.class)
            ))
    })
    void deleteContact(@PathVariable Long id);
}
