package com.back.desafio.core.usecase;

import com.back.desafio.core.domain.model.EventoModel;
import com.back.desafio.core.domain.model.EventoPaginacao;
import com.back.desafio.port.output.EventoOutputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BuscarEventoUseCaseTest {

    private EventoOutputPort eventoOutputPort;
    private BuscarEventoUseCase useCase;

    @BeforeEach
    void setUp() {
        eventoOutputPort = mock(EventoOutputPort.class);
        useCase = new BuscarEventoUseCase(eventoOutputPort);
    }

    @Test
    void deveBuscarEventosPaginados() {
        int pagina = 0;
        int tamanho = 10;
        int quantidade = 5;
        List<EventoModel> eventos = List.of(new EventoModel(1L, "Título", "Descrição", LocalDateTime.now(), "Local"), new EventoModel(1L, "Título", "Descrição", LocalDateTime.now(), "Local"));

        when(eventoOutputPort.buscarQuantidadeEventos()).thenReturn(quantidade);
        when(eventoOutputPort.buscarEventos(pagina, tamanho)).thenReturn(eventos);

        EventoPaginacao result = useCase.buscarEventos(pagina, tamanho);

        assertEquals(quantidade, result.quantidadeEvento());
        assertEquals(eventos, result.eventos());
        verify(eventoOutputPort).buscarQuantidadeEventos();
        verify(eventoOutputPort).buscarEventos(pagina, tamanho);
    }

    @Test
    void deveBuscarEventoPorId() {
        Long id = 1L;
        EventoModel evento = new EventoModel(1L, "Título", "Descrição", LocalDateTime.now(), "Local");

        when(eventoOutputPort.buscarEventoPorId(id)).thenReturn(evento);

        EventoModel result = useCase.buscarEventoPorId(id);

        assertEquals(evento, result);
        verify(eventoOutputPort).buscarEventoPorId(id);
    }
}
