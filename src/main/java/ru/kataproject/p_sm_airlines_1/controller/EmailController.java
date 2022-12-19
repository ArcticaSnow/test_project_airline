package ru.kataproject.p_sm_airlines_1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kataproject.p_sm_airlines_1.entity.Dto.ErrorResponseDto;

import java.util.List;

/**
 * Interface EmailController.
 * Declares Email API methods.
 *
 * @author Alexey Sen (alexey.sen@gmail.com)
 * @since 21.11.2022
 */

@Tag(name = "Email Controller")
@RequestMapping(EmailController.BASE_NAME)
public interface EmailController {
    /**
     * Major API version.
     */
    String MAJOR_VERSION = "/v1";
    /**
     * Base API name.
     */
    String BASE_NAME = MAJOR_VERSION + "/email";

    /**
     * Method sends email with optional attachment to passenger's email address.
     */
    @PostMapping(value = "/passenger/{id}")
    @Operation(
            method = "POST",
            summary = "Send email to passenger",
            description = "Send email with optional attachment"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Email successfully sent"
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
    void sendEmail(@PathVariable Long id,
                   @RequestParam(name = "subject", required = false) String subject,
                   @RequestParam(name = "attachment", required = false) String attachment,
                   @RequestBody String body);

    /**
     * Method sends email to many passenger's email addresses.
     * http://hostname:port/email/passengers?id=1,2,3...
     * http://hostname:port/email/passengers?id=1&id=2&id=3...
     */
    @PostMapping(value = "/passengers")
    @Operation(
            method = "POST",
            summary = "Send email to several passengers",
            description = "Send email to several passengers http(s)://host:post/email/passengers?id=1,2,3...."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Email successfully sent"
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
    void sendEmail(@RequestParam(name = "id") List<Long> ids,
                   @RequestParam(name = "subject", required = false) String subject,
                   @RequestBody String body);

    /**
     * Method sends email.
     */
    @PostMapping(value = "/to/{email}")
    @Operation(
            method = "POST",
            summary = "Send email by plain address",
            description = "Send email by plain address"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Email successfully sent"
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
    void sendSimpleEmail(@PathVariable("email") String email,
                         @RequestParam(name="subject", required = false) String subject,
                         @RequestBody String body);
}
