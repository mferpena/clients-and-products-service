package com.mferpena.infrastructure.secondary.persistence.mappers;

import com.mferpena.core.domain.models.Order;
import com.mferpena.infrastructure.secondary.persistence.entities.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderEntity toEntity(Order order);

    Order toDomain(OrderEntity orderEntity);
}
