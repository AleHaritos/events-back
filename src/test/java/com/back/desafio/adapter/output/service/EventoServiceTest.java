package com.back.desafio.adapter.output.service;

import com.back.desafio.adapter.exception.NotFoundException;
import com.back.desafio.adapter.output.entity.EventoEntity;
import com.back.desafio.adapter.output.mapper.EventoOutputMapper;
import com.back.desafio.adapter.output.repository.EventoRepository;
import com.back.desafio.core.domain.model.EventoModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class EventoServiceTest {

    private EventoRepository eventoRepository;
    private EventoOutputMapper mapper;
    private EventoService service;

    @BeforeEach
    void setUp() {
        eventoRepository = mock(EventoRepository.class);
        mapper = mock(EventoOutputMapper.class);
        service = new EventoService(eventoRepository, mapper);
    }

    @Test
    void deveSalvarEvento() {
        EventoModel model = new EventoModel(1L, "Título", "Descrição", LocalDateTime.now(), "Local");
        EventoEntity entity = new EventoEntity();

        when(mapper.toEntity(model)).thenReturn(entity);
        when(eventoRepository.save(entity)).thenReturn(entity);
        when(mapper.toModel(entity)).thenReturn(model);

        EventoModel result = service.salvarEvento(model);

        assertEquals(model, result);
        verify(eventoRepository).save(entity);
    }

    @Test
    void deveBuscarEventosPaginados() {
        EventoEntity entity = new EventoEntity();
        List<EventoEntity> entities = List.of(entity);
        List<EventoModel> models = List.of(new EventoModel(1L, "Título", "Descrição", LocalDateTime.now(), "Local"));

        when(eventoRepository.findByAtivoTrue(PageRequest.of(0, 10))).thenReturn(new PageImpl<>(entities));
        when(mapper.toModelList(entities)).thenReturn(models);

        List<EventoModel> result = service.buscarEventos(0, 10);

        assertEquals(models, result);
        verify(eventoRepository).findByAtivoTrue(PageRequest.of(0, 10));
    }

    @Test
    void deveBuscarEventoPorId() {
        Long id = 1L;
        EventoEntity entity = new EventoEntity();
        EventoModel model = new EventoModel(1L, "Título", "Descrição", LocalDateTime.now(), "Local");

        when(eventoRepository.findByIdAndAtivoTrue(id)).thenReturn(Optional.of(entity));
        when(mapper.toModel(entity)).thenReturn(model);

        EventoModel result = service.buscarEventoPorId(id);

        assertEquals(model, result);
        verify(eventoRepository).findByIdAndAtivoTrue(id);
    }

    @Test
    void deveLancarExcecaoAoBuscarEventoPorIdInexistente() {
        Long id = 99L;
        when(eventoRepository.findByIdAndAtivoTrue(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.buscarEventoPorId(id));
    }

    @Test
    void deveAlterarEvento() {
        Long id = 1L;
        EventoModel model = new EventoModel(id, "Novo", "Alterado", LocalDateTime.now(), "Local");
        EventoEntity entity = new EventoEntity();
        EventoEntity atualizado = new EventoEntity();

        when(eventoRepository.findByIdAndAtivoTrue(id)).thenReturn(Optional.of(entity));
        when(eventoRepository.save(entity)).thenReturn(atualizado);
        when(mapper.toModel(atualizado)).thenReturn(model);

        EventoModel result = service.alterarEvento(model, id);

        assertEquals(model, result);
        verify(eventoRepository).save(entity);
    }

    @Test
    void deveLancarExcecaoAoAlterarEventoInexistente() {
        Long id = 99L;
        EventoModel model = new EventoModel(1L, "Título", "Descrição", LocalDateTime.now(), "Local");

        when(eventoRepository.findByIdAndAtivoTrue(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.alterarEvento(model, id));
    }

    @Test
    void deveDesativarEvento() {
        Long id = 1L;
        service.deletarEvento(id);
        verify(eventoRepository).desativarEvento(id);
    }

    @Test
    void deveBuscarQuantidadeDeEventosAtivos() {
        when(eventoRepository.buscarQuantidadeEventosAtivos()).thenReturn(5);
        Integer result = service.buscarQuantidadeEventos();
        assertEquals(5, result);
        verify(eventoRepository).buscarQuantidadeEventosAtivos();
    }
}

