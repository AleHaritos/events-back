package com.back.desafio.core.usecase;

import com.back.desafio.core.domain.model.EventoModel;
import com.back.desafio.port.input.AlterarEventoInputPort;
import com.back.desafio.port.output.EventoOutputPort;

public class AlterarEventoUseCase implements AlterarEventoInputPort {

    private final EventoOutputPort eventoOutputPort;

    public AlterarEventoUseCase(EventoOutputPort eventoOutputPort) {
        this.eventoOutputPort = eventoOutputPort;
    }

    @Override
    public EventoModel alterarEvento(EventoModel evento, Long id) {
        return eventoOutputPort.alterarEvento(evento, id);
    }
}
