package com.mferpena.core.port;

import com.mferpena.core.domain.models.Client;
import com.mferpena.core.domain.models.CustomerDetail;
import com.mferpena.core.domain.models.UserInfo;

import java.util.List;
import java.util.Optional;

public interface ClientPersistencePort {
    void save(Client client);

    List<CustomerDetail> findAll();

    Optional<Client> findById(Long id);

    void update(Client existClient);

    UserInfo getById(Long id);

    void deleteById(Long id);
}
