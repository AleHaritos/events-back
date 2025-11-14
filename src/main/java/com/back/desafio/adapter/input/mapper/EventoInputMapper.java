package com.back.desafio.adapter.input.mapper;

import com.back.desafio.adapter.input.controller.dto.request.EventoRequest;
import com.back.desafio.adapter.input.controller.dto.response.EventoPaginacaoResponse;
import com.back.desafio.adapter.input.controller.dto.response.EventoResponse;
import com.back.desafio.core.domain.model.EventoModel;
import com.back.desafio.core.domain.model.EventoPaginacao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventoInputMapper {

    EventoModel toModel(EventoRequest request);

    EventoResponse toResponse(EventoModel model);

    EventoPaginacaoResponse toResponsePaginacao(EventoPaginacao eventoPaginacao);

}
