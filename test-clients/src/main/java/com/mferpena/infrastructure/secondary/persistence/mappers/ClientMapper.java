package com.mferpena.infrastructure.secondary.persistence.mappers;

import com.mferpena.core.domain.models.Client;
import com.mferpena.core.domain.models.CustomerDetail;
import com.mferpena.infrastructure.secondary.persistence.entities.ClientEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientMapper {
    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    @Mapping(target = "id", ignore = true)
    ClientEntity toDtoIgnoreId(Client client);

    ClientEntity toDto(Client client);

    Client clientToDto(ClientEntity clientEntity);

    @Mapping(target = "fullName", source = ".", qualifiedByName = "fullNameMapper")
    CustomerDetail customerDetailToDomain(ClientEntity clientEntity);

    @Named("fullNameMapper")
    default String mapFullName(ClientEntity clientEntity) {
        String fullName = clientEntity.getName() + " " + clientEntity.getPaternalLastName();

        if (clientEntity.getMaternalLastName() != null && !clientEntity.getMaternalLastName().isEmpty()) {
            fullName += " " + clientEntity.getMaternalLastName();
        }

        return fullName;
    }
}
