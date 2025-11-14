package com.back.desafio.adapter.input.controller.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
public class EventoRequest {

    @NotBlank(message = "O título não deve ser nulo")
    @Size(message = "O título não deve ter mais de 100 caractéres", max = 100)
    private String titulo;

    @NotBlank(message = "A descrição não deve ser nulo")
    @Size(message = "A descrição não deve ter mais de 1000 caractéres", max = 1000)
    private String descricao;

    @NotNull(message = "A data e hora são obrigatórias")
    @FutureOrPresent(message = "Não é permitido datas retroativas.")
    private LocalDateTime dataHoraEvento;

    @NotBlank(message = "O local não deve ser nulo")
    @Size(message = "O local não deve ter mais de 200 caractéres", max = 200)
    private String local;


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataHoraEvento() {
        return dataHoraEvento;
    }

    public void setDataHoraEvento(LocalDateTime dataHoraEvento) {
        this.dataHoraEvento = dataHoraEvento;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}
