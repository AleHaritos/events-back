package com.back.desafio.adapter.input.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class EventoPaginacaoResponse {

    private Integer quantidadeEvento;

    private List<EventoResponse> eventos;

    public Integer getQuantidadeEvento() {
        return quantidadeEvento;
    }

    public void setQuantidadeEvento(Integer quantidadeEvento) {
        this.quantidadeEvento = quantidadeEvento;
    }

    public List<EventoResponse> getEventos() {
        return eventos;
    }

    public void setEventos(List<EventoResponse> eventos) {
        this.eventos = eventos;
    }
}
