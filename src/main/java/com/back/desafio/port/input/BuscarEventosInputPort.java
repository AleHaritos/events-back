package com.back.desafio.port.input;

import com.back.desafio.core.domain.model.EventoModel;
import com.back.desafio.core.domain.model.EventoPaginacao;

public interface BuscarEventosInputPort {

    EventoPaginacao buscarEventos(Integer pagina, Integer tamanhoPagina);

    EventoModel buscarEventoPorId(Long id);

}
