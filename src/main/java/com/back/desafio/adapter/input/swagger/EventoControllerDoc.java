package com.back.desafio.adapter.input.swagger;

import com.back.desafio.adapter.input.controller.dto.request.EventoRequest;
import com.back.desafio.adapter.input.controller.dto.response.EventoPaginacaoResponse;
import com.back.desafio.adapter.input.controller.dto.response.EventoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

public interface EventoControllerDoc {

    @Operation(summary = "Cria um novo evento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Evento criado com sucesso",
                    content = @Content(schema = @Schema(implementation = EventoResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
    })
    @PostMapping
    EventoResponse salvarEvento(@RequestBody @Valid EventoRequest request);

    @Operation(summary = "Busca eventos paginados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Eventos encontrados",
                    content = @Content(schema = @Schema(implementation = EventoPaginacaoResponse.class)))
    })
    @GetMapping
    EventoPaginacaoResponse buscarEventos(
            @Parameter(description = "Número da página") @RequestParam("pagina") Integer pagina,
            @Parameter(description = "Tamanho da página") @RequestParam("tamanhoPagina") Integer tamanhoPagina
    );

    @Operation(summary = "Busca evento por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evento encontrado",
                    content = @Content(schema = @Schema(implementation = EventoResponse.class))),
            @ApiResponse(responseCode = "404", description = "Evento não encontrado", content = @Content)
    })
    @GetMapping("/{id}")
    EventoResponse buscarEventoPorId(@PathVariable("id") Long id);

    @Operation(summary = "Altera um evento existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evento alterado com sucesso",
                    content = @Content(schema = @Schema(implementation = EventoResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Evento não encontrado", content = @Content)
    })
    @PutMapping("/{id}")
    EventoResponse alterarEvento(@RequestBody @Valid EventoRequest request, @PathVariable("id") Long id);

    @Operation(summary = "Deleta um evento por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Evento deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Evento não encontrado", content = @Content)
    })
    @DeleteMapping("/{id}")
    void deletarEventoPorId(@PathVariable("id") Long id);
}

