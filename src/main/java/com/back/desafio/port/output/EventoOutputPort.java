package com.back.desafio.port.output;

import com.back.desafio.core.domain.model.EventoModel;

import java.util.List;

public interface EventoOutputPort {

    EventoModel salvarEvento(EventoModel evento);

    List<EventoModel> buscarEventos(Integer pagina, Integer tamanhoPagina);

    EventoModel buscarEventoPorId(Long id);

    EventoModel alterarEvento(EventoModel evento, Long id);

    void deletarEvento(Long id);

    Integer buscarQuantidadeEventos();

}
