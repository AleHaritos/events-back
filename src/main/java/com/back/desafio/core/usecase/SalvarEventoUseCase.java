package com.back.desafio.core.usecase;

import com.back.desafio.core.domain.model.EventoModel;
import com.back.desafio.port.input.SalvarEventoInputPort;
import com.back.desafio.port.output.EventoOutputPort;

public class SalvarEventoUseCase implements SalvarEventoInputPort {

    private final EventoOutputPort eventoOutputPort;

    public SalvarEventoUseCase(EventoOutputPort eventoOutputPort) {
        this.eventoOutputPort = eventoOutputPort;
    }

    @Override
    public EventoModel salvarEvento(EventoModel evento) {
        return eventoOutputPort.salvarEvento(evento);
    }
}
