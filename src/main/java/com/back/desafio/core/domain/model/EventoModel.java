package com.back.desafio.core.domain.model;

import java.time.LocalDateTime;

public record EventoModel(
        Long id,
        String titulo,
        String descricao,
        LocalDateTime dataHoraEvento,
        String local
) {
}
