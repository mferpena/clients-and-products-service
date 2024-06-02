package com.mferpena.core.port;

import com.mferpena.core.domain.models.Order;
import com.mferpena.core.domain.models.Product;

import java.util.Optional;

public interface ProductPersistencePort {
    Optional<Product> findById(Long productId);

    Product save(Product product, Order order);
}
