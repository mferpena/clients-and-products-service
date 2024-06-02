package com.mferpena.infrastructure.secondary.persistence.repositories;

import com.mferpena.infrastructure.secondary.persistence.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
}
