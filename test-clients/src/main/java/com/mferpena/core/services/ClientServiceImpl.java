package com.mferpena.core.services;

import com.mferpena.core.ClientUseCase;
import com.mferpena.core.domain.models.Client;
import com.mferpena.core.domain.models.CustomerDetail;
import com.mferpena.core.port.ClientPersistencePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientUseCase {
    private final ClientPersistencePort clientPersistencePort;

    @Override
    public void createClient(Client client) {
        client.setStatus(true);
        clientPersistencePort.save(client);
    }

    @Override
    public List<CustomerDetail> listClient() {
        return clientPersistencePort.findAll();
    }

    @Override
    public void updateClient(Client client) {
        Client existClient = clientPersistencePort.findById(client.getId())
                .orElseThrow(() -> new IllegalArgumentException("Client not found with ID: " + client.getId()));
        existClient.setName(client.getName());
        existClient.setPaternalLastName(client.getPaternalLastName());
        existClient.setMaternalLastName(client.getMaternalLastName());
        clientPersistencePort.update(existClient);
    }
}
