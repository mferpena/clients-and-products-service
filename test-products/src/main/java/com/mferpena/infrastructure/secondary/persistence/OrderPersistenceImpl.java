package com.mferpena.infrastructure.secondary.persistence;

import com.mferpena.core.domain.models.Order;
import com.mferpena.core.port.OrderPersistencePort;
import com.mferpena.infrastructure.secondary.persistence.entities.OrderEntity;
import com.mferpena.infrastructure.secondary.persistence.mappers.OrderMapper;
import com.mferpena.infrastructure.secondary.persistence.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderPersistenceImpl implements OrderPersistencePort {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public Optional<Order> findById(Long orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with ID: " + orderId));
        return Optional.ofNullable(orderMapper.toDomain(orderEntity));
    }
}
