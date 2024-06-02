package com.mferpena.infrastructure.secondary.persistence;

import com.mferpena.core.domain.models.Order;
import com.mferpena.core.domain.models.Product;
import com.mferpena.core.port.ProductPersistencePort;
import com.mferpena.infrastructure.secondary.persistence.entities.OrderEntity;
import com.mferpena.infrastructure.secondary.persistence.entities.ProductEntity;
import com.mferpena.infrastructure.secondary.persistence.mappers.OrderMapper;
import com.mferpena.infrastructure.secondary.persistence.mappers.ProductMapper;
import com.mferpena.infrastructure.secondary.persistence.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductPersistenceImpl implements ProductPersistencePort {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final OrderMapper orderMapper;

    @Override
    public Optional<Product> findById(Long productId) {
        ProductEntity orderEntity = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with ID: " + productId));
        return Optional.ofNullable(productMapper.toDomain(orderEntity));
    }

    @Override
    public Product save(Product product, Order order) {
        OrderEntity orderEntity = orderMapper.toEntity(order);
        ProductEntity productEntity = productMapper.toEntity(product);
        productEntity.setOrder(orderEntity);
        return productMapper.toDomain(productRepository.save(productEntity));
    }
}
