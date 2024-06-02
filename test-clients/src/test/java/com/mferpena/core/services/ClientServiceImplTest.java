package com.mferpena.core.services;

import com.mferpena.AbstractTest;
import com.mferpena.core.domain.models.Client;
import com.mferpena.core.domain.models.CustomerDetail;
import com.mferpena.core.port.ClientPersistencePort;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientServiceImplTest extends AbstractTest {
    @Mock
    private ClientPersistencePort clientPersistencePort;
    @InjectMocks
    private ClientServiceImpl clientUseCase;

    @Test
    void createSuccess() throws Exception {
        Client client = convertToObject("/templates/core/services/client.json", Client.class);
        clientUseCase.createClient(client);

        verify(clientPersistencePort, times(1)).save(client);
    }

    @Test
    void listClientSuccess() throws Exception {
        CustomerDetail customerDetail = convertToObject("/templates/core/services/customerDetail.json", CustomerDetail.class);
        List<CustomerDetail> expectedCustomers = Arrays.asList(customerDetail, new CustomerDetail());
        when(clientPersistencePort.findAll()).thenReturn(expectedCustomers);

        List<CustomerDetail> actualCustomers = clientUseCase.listClient();

        assertEquals(expectedCustomers.size(), actualCustomers.size());
        verify(clientPersistencePort, times(1)).findAll();
    }

    @Test
    void updateClientSuccess() throws Exception {
        Client client = convertToObject("/templates/core/services/client.json", Client.class);
        client.setId(1L);
        when(clientPersistencePort.findById(client.getId())).thenReturn(Optional.of(client));

        clientUseCase.updateClient(client);

        verify(clientPersistencePort, times(1)).update(client);
    }

    @Test
    void updateClientFailureClientNotFound() {
        Client client = new Client();
        client.setId(-1L);
        when(clientPersistencePort.findById(client.getId())).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> clientUseCase.updateClient(client));

        assertTrue(exception.getMessage().contains("Client not found with ID:"));
        verify(clientPersistencePort, times(1)).findById(client.getId());
        verify(clientPersistencePort, never()).update(any(Client.class));
    }
}