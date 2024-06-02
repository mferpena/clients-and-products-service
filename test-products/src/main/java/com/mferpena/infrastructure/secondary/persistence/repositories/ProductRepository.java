package com.mferpena.infrastructure.secondary.persistence.repositories;

import com.mferpena.infrastructure.secondary.persistence.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
