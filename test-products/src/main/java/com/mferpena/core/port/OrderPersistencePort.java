package com.mferpena.core.port;

import com.mferpena.core.domain.models.Order;

import java.util.Optional;

public interface OrderPersistencePort {
    Optional<Order> findById(Long orderId);
}
