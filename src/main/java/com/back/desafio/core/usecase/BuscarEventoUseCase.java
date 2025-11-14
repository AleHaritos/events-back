package com.back.desafio.core.usecase;

import com.back.desafio.core.domain.model.EventoModel;
import com.back.desafio.core.domain.model.EventoPaginacao;
import com.back.desafio.port.input.BuscarEventosInputPort;
import com.back.desafio.port.output.EventoOutputPort;

import java.util.List;

public class BuscarEventoUseCase implements BuscarEventosInputPort {

    private final EventoOutputPort eventoOutputPort;

    public BuscarEventoUseCase(EventoOutputPort eventoOutputPort) {
        this.eventoOutputPort = eventoOutputPort;
    }


    @Override
    public EventoPaginacao buscarEventos(Integer pagina, Integer tamanhoPagina) {
        Integer quantidadeEventos = eventoOutputPort.buscarQuantidadeEventos();
        List<EventoModel> listaEventos = eventoOutputPort.buscarEventos(pagina, tamanhoPagina);
        return new EventoPaginacao(quantidadeEventos, listaEventos);
    }

    @Override
    public EventoModel buscarEventoPorId(Long id) {
        return eventoOutputPort.buscarEventoPorId(id);
    }
}
