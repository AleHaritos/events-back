package com.back.desafio.core.usecase;

import com.back.desafio.port.input.DeletarEventoInputPort;
import com.back.desafio.port.output.EventoOutputPort;

public class DeletarEventoUseCase implements DeletarEventoInputPort {

    private final EventoOutputPort eventoOutputPort;

    public DeletarEventoUseCase(EventoOutputPort eventoOutputPort) {
        this.eventoOutputPort = eventoOutputPort;
    }

    @Override
    public void deletarEventoPorId(Long id) {
        eventoOutputPort.deletarEvento(id);
    }
}
