package com.cooperl.hello.contracts;

import com.cooperl.hello.models.Hello;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping(value = "hello")
@Tag(name = "Hello", description = "Hello")
public interface ApiHello {

    @Operation(summary = "Liste des hello", description = "Liste des hello", tags={})
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Hello[].class))))
    })
    @RequestMapping(
        produces = { "application/json" },
        method = RequestMethod.GET
    )
    Flux<Hello> getHellos();


    @Operation(summary = "Information d'un hello", description = "Information d'un hello", tags={})
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Hello.class)))
    })
    @RequestMapping(
        value = "{codeHello}",
        produces = { "application/json" },
        method = RequestMethod.GET
    )
    Mono<Hello> getHello(@Parameter(in = ParameterIn.PATH, description = "Code du hello", required=true, schema=@Schema()) @PathVariable("codeHello") String codeHello);

}

