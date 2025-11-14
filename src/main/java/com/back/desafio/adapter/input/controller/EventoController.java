package com.back.desafio.adapter.input.controller;


import com.back.desafio.adapter.input.controller.dto.request.EventoRequest;
import com.back.desafio.adapter.input.controller.dto.response.EventoPaginacaoResponse;
import com.back.desafio.adapter.input.controller.dto.response.EventoResponse;
import com.back.desafio.adapter.input.mapper.EventoInputMapper;
import com.back.desafio.adapter.input.swagger.EventoControllerDoc;
import com.back.desafio.core.domain.model.EventoModel;
import com.back.desafio.port.input.AlterarEventoInputPort;
import com.back.desafio.port.input.BuscarEventosInputPort;
import com.back.desafio.port.input.DeletarEventoInputPort;
import com.back.desafio.port.input.SalvarEventoInputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/events")
@RequiredArgsConstructor
@Slf4j
public class EventoController implements EventoControllerDoc {

    private final EventoInputMapper mapper;

    private final BuscarEventosInputPort buscarEventosInputPort;
    private final DeletarEventoInputPort deletarEventoInputPort;
    private final SalvarEventoInputPort salvarEventoInputPort;
    private final AlterarEventoInputPort alterarEventoInputPort;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventoResponse salvarEvento(@RequestBody @Valid EventoRequest request) {
        log.info("Salvando evento...");
        EventoModel response = salvarEventoInputPort.salvarEvento(mapper.toModel(request));
        log.info("Evento salvo com sucesso.");
        return mapper.toResponse(response);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public EventoPaginacaoResponse buscarEventos(
            @RequestParam("pagina") Integer pagina,
            @RequestParam("tamanhoPagina") Integer tamanhoPagina
    ) {
        log.info("Buscando eventos...");
        return mapper.toResponsePaginacao(buscarEventosInputPort.buscarEventos(pagina, tamanhoPagina));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EventoResponse buscarEventoPorId(@PathVariable(value = "id") Long id) {
        log.info("Buscando evento por id: {}", id);
        return mapper.toResponse(buscarEventosInputPort.buscarEventoPorId(id));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EventoResponse alterarEvento(
            @RequestBody @Valid EventoRequest request,
            @PathVariable(value = "id") Long id
            ) {
        log.info("Alterando evento do id: {}", id);
        return mapper.toResponse(alterarEventoInputPort.alterarEvento(
                mapper.toModel(request),
                id
        ));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarEventoPorId(@PathVariable(value = "id") Long id) {
        log.info("Deletando evento do id: {}", id);
        deletarEventoInputPort.deletarEventoPorId(id);
    }
}
