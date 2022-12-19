package ru.kataproject.p_sm_airlines_1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Main example controller")
@RequestMapping(MainController.BASE_NAME)
public interface MainController {

    String MAJOR_VERSION = "/v1";
    String BASE_NAME = MAJOR_VERSION + "/main";

    @GetMapping()
    @Operation(summary = "Get index", description = "Returnes main default page")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Page successfully returned", content = @Content),
            @ApiResponse(responseCode = "400", description = "Page not found", content = @Content)
    })
    ResponseEntity<String> index();
}