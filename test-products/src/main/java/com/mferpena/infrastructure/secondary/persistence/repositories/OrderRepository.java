package com.mferpena.infrastructure.secondary.persistence.repositories;

import com.mferpena.infrastructure.secondary.persistence.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
