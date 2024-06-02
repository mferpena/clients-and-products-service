package com.mferpena.infrastructure.secondary.persistence.mappers;

import com.mferpena.core.domain.models.Product;
import com.mferpena.infrastructure.secondary.persistence.entities.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product toDomain(ProductEntity productEntity);

    ProductEntity toEntity(Product product);
}
