package com.mferpena.infrastructure.secondary.persistence;

import com.mferpena.core.domain.models.Client;
import com.mferpena.core.domain.models.CustomerDetail;
import com.mferpena.core.domain.models.UserInfo;
import com.mferpena.core.port.ClientPersistencePort;
import com.mferpena.infrastructure.secondary.persistence.entities.ClientEntity;
import com.mferpena.infrastructure.secondary.persistence.mappers.ClientMapper;
import com.mferpena.infrastructure.secondary.persistence.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Primary
@Service("dbImpl")
@RequiredArgsConstructor
public class ClientPersistenceImpl implements ClientPersistencePort {
    private final ClientMapper clientMapper;
    private final ClientRepository clientRepository;

    @Override
    public void save(Client client) {
        clientRepository.save(clientMapper.toDto(client));
    }

    @Override
    public List<CustomerDetail> findAll() {
        List<ClientEntity> clientEntities = clientRepository.findAll();
        return clientEntities.stream()
                .map(clientMapper::customerDetailToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Client> findById(Long id) {
        ClientEntity clientEntity = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Client not found with ID: " + id));
        return Optional.ofNullable(clientMapper.clientToDto(clientEntity));
    }

    @Override
    public void update(Client existClient) {
        clientRepository.save(clientMapper.toDto(existClient));
    }

    @Override
    public UserInfo getById(Long id) {
        ClientEntity clientEntity = clientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Client not found with ID: " + id));
        return clientMapper.toUserInfo(clientEntity);
    }

    @Override
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }
}
