package com.back.desafio.port.input;

import com.back.desafio.core.domain.model.EventoModel;

public interface AlterarEventoInputPort {

    EventoModel alterarEvento(EventoModel evento, Long id);

}
