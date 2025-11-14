package com.back.desafio.config;

import com.back.desafio.core.usecase.AlterarEventoUseCase;
import com.back.desafio.core.usecase.BuscarEventoUseCase;
import com.back.desafio.core.usecase.DeletarEventoUseCase;
import com.back.desafio.core.usecase.SalvarEventoUseCase;
import com.back.desafio.port.input.AlterarEventoInputPort;
import com.back.desafio.port.input.BuscarEventosInputPort;
import com.back.desafio.port.input.DeletarEventoInputPort;
import com.back.desafio.port.input.SalvarEventoInputPort;
import com.back.desafio.port.output.EventoOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public AlterarEventoInputPort alterarEventoUseCase(EventoOutputPort eventoOutputPort) {
        return new AlterarEventoUseCase(eventoOutputPort);
    }

    @Bean
    public BuscarEventosInputPort buscarEventoUseCase(EventoOutputPort eventoOutputPort) {
        return new BuscarEventoUseCase(eventoOutputPort);
    }

    @Bean
    public DeletarEventoInputPort deletarEventoUseCase(EventoOutputPort eventoOutputPort) {
        return new DeletarEventoUseCase(eventoOutputPort);
    }

    @Bean
    public SalvarEventoInputPort salvarEventoUseCase(EventoOutputPort eventoOutputPort) {
        return new SalvarEventoUseCase(eventoOutputPort);
    }

}
