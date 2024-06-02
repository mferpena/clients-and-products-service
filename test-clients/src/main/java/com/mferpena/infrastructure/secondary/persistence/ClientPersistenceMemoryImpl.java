package com.mferpena.infrastructure.secondary.persistence;

import com.mferpena.core.domain.models.Client;
import com.mferpena.core.domain.models.CustomerDetail;
import com.mferpena.core.port.ClientPersistencePort;
import com.mferpena.infrastructure.secondary.persistence.entities.ClientEntity;
import com.mferpena.infrastructure.secondary.persistence.mappers.ClientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Deprecated
@Service("memoryImpl")
@RequiredArgsConstructor
public class ClientPersistenceMemoryImpl implements ClientPersistencePort {
    private final ClientMapper clientMapper;
    private final List<ClientEntity> clientEntities = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(10000);

    @Override
    public void save(Client client) {
        ClientEntity clientEntity = clientMapper.toDtoIgnoreId(client);

        if (clientEntity.getId() == null) {
            clientEntity.setId(idGenerator.getAndIncrement());
        } else {
            clientEntities.removeIf(c -> c.getId().equals(clientEntity.getId()));
        }

        clientEntity.setDateCreate(new Date());
        clientEntities.add(clientEntity);
    }

    @Override
    public List<CustomerDetail> findAll() {
        return clientEntities.stream()
                .map(clientMapper::customerDetailToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Client> findById(Long id) {
        return clientEntities.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .map(clientMapper::clientToDto);
    }

    @Override
    public void update(Client client) {
        int index = IntStream.range(0, clientEntities.size())
                .filter(i -> client.getId().equals(clientEntities.get(i).getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Client not found with ID: " + client.getId()));

        ClientEntity existingClientEntity = clientEntities.get(index);

        Optional.ofNullable(client.getName()).ifPresent(existingClientEntity::setName);
        Optional.ofNullable(client.getPaternalLastName()).ifPresent(existingClientEntity::setPaternalLastName);
        Optional.ofNullable(client.getMaternalLastName()).ifPresent(existingClientEntity::setMaternalLastName);
    }
}
