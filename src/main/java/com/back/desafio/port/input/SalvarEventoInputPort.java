package com.back.desafio.port.input;

import com.back.desafio.core.domain.model.EventoModel;

public interface SalvarEventoInputPort {

    EventoModel salvarEvento(EventoModel evento);

}
