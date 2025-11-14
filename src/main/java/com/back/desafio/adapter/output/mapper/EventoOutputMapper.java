package com.back.desafio.adapter.output.mapper;

import com.back.desafio.adapter.output.dto.EventoOutputRequest;
import com.back.desafio.adapter.output.entity.EventoEntity;
import com.back.desafio.core.domain.model.EventoModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventoOutputMapper {

    EventoOutputRequest toRequest(EventoModel model);

    EventoModel toModel(EventoEntity entity);

    List<EventoModel> toModelList(List<EventoEntity> entityList);

    EventoEntity toEntity(EventoModel eventoModel);


}
