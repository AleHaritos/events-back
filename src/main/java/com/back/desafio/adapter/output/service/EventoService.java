package com.back.desafio.adapter.output.service;

import com.back.desafio.adapter.exception.NotFoundException;
import com.back.desafio.adapter.output.entity.EventoEntity;
import com.back.desafio.adapter.output.mapper.EventoOutputMapper;
import com.back.desafio.adapter.output.repository.EventoRepository;
import com.back.desafio.core.domain.model.EventoModel;
import com.back.desafio.port.output.EventoOutputPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventoService implements EventoOutputPort {

    private final EventoRepository eventoRepository;
    private final EventoOutputMapper mapper;

    @Override
    public EventoModel salvarEvento(EventoModel evento) {
        EventoEntity entity = eventoRepository.save(mapper.toEntity(evento));
        return mapper.toModel(entity);
    }

    @Override
    public List<EventoModel> buscarEventos(Integer pagina, Integer tamanhoPagina) {
        Pageable pageable = PageRequest.of(pagina, tamanhoPagina);
        List<EventoEntity> listaEvento = eventoRepository.findByAtivoTrue(pageable).stream().toList();
        return mapper.toModelList(listaEvento);
    }

    @Override
    public EventoModel buscarEventoPorId(Long id) {
        EventoEntity entity = eventoRepository.findByIdAndAtivoTrue(id).orElseThrow(() -> new NotFoundException("Evento: " + id + ", não encontrado."));
        return mapper.toModel(entity);
    }

    @Override
    public EventoModel alterarEvento(EventoModel evento, Long id) {
        EventoEntity eventoEntity = eventoRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new NotFoundException("Evento ativo com ID " + id + " não encontrado"));

        eventoEntity.setTitulo(evento.titulo());
        eventoEntity.setDescricao(evento.descricao());
        eventoEntity.setDataHoraEvento(evento.dataHoraEvento());
        eventoEntity.setLocal(evento.local());

        EventoEntity eventoAtualizado = eventoRepository.save(eventoEntity);
        return mapper.toModel(eventoAtualizado);
    }

    @Override
    @Transactional
    public void deletarEvento(Long id) {
        eventoRepository.desativarEvento(id);
    }

    @Override
    public Integer buscarQuantidadeEventos() {
        return eventoRepository.buscarQuantidadeEventosAtivos();
    }
}
