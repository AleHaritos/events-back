package com.back.desafio.adapter.input.controller;

import com.back.desafio.adapter.input.controller.dto.request.EventoRequest;
import com.back.desafio.adapter.input.controller.dto.response.EventoPaginacaoResponse;
import com.back.desafio.adapter.input.controller.dto.response.EventoResponse;
import com.back.desafio.adapter.input.mapper.EventoInputMapper;
import com.back.desafio.core.domain.model.EventoModel;
import com.back.desafio.port.input.AlterarEventoInputPort;
import com.back.desafio.port.input.BuscarEventosInputPort;
import com.back.desafio.port.input.DeletarEventoInputPort;
import com.back.desafio.port.input.SalvarEventoInputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EventoControllerTest {

    private EventoInputMapper mapper;
    private BuscarEventosInputPort buscarEventosInputPort;
    private DeletarEventoInputPort deletarEventoInputPort;
    private SalvarEventoInputPort salvarEventoInputPort;
    private AlterarEventoInputPort alterarEventoInputPort;

    private EventoController controller;

    @BeforeEach
    void setUp() {
        mapper = mock(EventoInputMapper.class);
        buscarEventosInputPort = mock(BuscarEventosInputPort.class);
        deletarEventoInputPort = mock(DeletarEventoInputPort.class);
        salvarEventoInputPort = mock(SalvarEventoInputPort.class);
        alterarEventoInputPort = mock(AlterarEventoInputPort.class);

        controller = new EventoController(mapper, buscarEventosInputPort, deletarEventoInputPort, salvarEventoInputPort, alterarEventoInputPort);
    }

    @Test
    void deveSalvarEvento() {
        EventoRequest request = new EventoRequest();
        EventoModel model = new EventoModel(1L, "titulo", "descricao", LocalDateTime.now(), "SP");
        EventoResponse response = new EventoResponse();

        when(mapper.toModel(request)).thenReturn(model);
        when(salvarEventoInputPort.salvarEvento(model)).thenReturn(model);
        when(mapper.toResponse(model)).thenReturn(response);

        EventoResponse result = controller.salvarEvento(request);

        assertEquals(response, result);
        verify(salvarEventoInputPort).salvarEvento(model);
    }

    @Test
    void deveBuscarEventosPaginados() {
        EventoPaginacaoResponse paginacaoResponse = new EventoPaginacaoResponse();

        when(buscarEventosInputPort.buscarEventos(0, 10)).thenReturn(null);
        when(mapper.toResponsePaginacao(null)).thenReturn(paginacaoResponse);

        EventoPaginacaoResponse result = controller.buscarEventos(0, 10);

        assertEquals(paginacaoResponse, result);
        verify(buscarEventosInputPort).buscarEventos(0, 10);
    }

    @Test
    void deveBuscarEventoPorId() {
        Long id = 1L;
        EventoModel model = new EventoModel(1L, "titulo", "descricao", LocalDateTime.now(), "SP");
        EventoResponse response = new EventoResponse();

        when(buscarEventosInputPort.buscarEventoPorId(id)).thenReturn(model);
        when(mapper.toResponse(model)).thenReturn(response);

        EventoResponse result = controller.buscarEventoPorId(id);

        assertEquals(response, result);
        verify(buscarEventosInputPort).buscarEventoPorId(id);
    }

    @Test
    void deveAlterarEvento() {
        Long id = 1L;
        EventoRequest request = new EventoRequest();
        EventoModel model = new EventoModel(1L, "titulo", "descricao", LocalDateTime.now(), "SP");
        EventoResponse response = new EventoResponse();

        when(mapper.toModel(request)).thenReturn(model);
        when(alterarEventoInputPort.alterarEvento(model, id)).thenReturn(model);
        when(mapper.toResponse(model)).thenReturn(response);

        EventoResponse result = controller.alterarEvento(request, id);

        assertEquals(response, result);
        verify(alterarEventoInputPort).alterarEvento(model, id);
    }

    @Test
    void deveDeletarEventoPorId() {
        Long id = 1L;

        controller.deletarEventoPorId(id);

        verify(deletarEventoInputPort).deletarEventoPorId(id);
    }
}
