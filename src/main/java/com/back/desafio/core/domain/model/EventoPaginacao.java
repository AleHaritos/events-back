package com.back.desafio.core.domain.model;

import java.util.List;

public record EventoPaginacao(
        Integer quantidadeEvento,
        List<EventoModel> eventos
) {
}
