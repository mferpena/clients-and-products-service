package com.mferpena.infrastructure.primary.controllers.mapper;

import com.mferpena.core.domain.models.Client;
import com.mferpena.infrastructure.primary.controllers.dto.CreateClientDto;
import com.mferpena.infrastructure.primary.controllers.dto.UpdateClientDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientDtoMapper {
    ClientDtoMapper INSTANCE = Mappers.getMapper(ClientDtoMapper.class);

    Client toDomain(CreateClientDto createClientDto);

    @Mapping(target = "id", source = "updateClientDto.id")
    Client toDomain(UpdateClientDto updateClientDto);
}
