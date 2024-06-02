package com.mferpena.core;

import com.mferpena.core.domain.models.Client;
import com.mferpena.core.domain.models.CustomerDetail;

import java.util.List;

public interface ClientUseCase {
    void createClient(Client client);

    List<CustomerDetail> listClient();

    void updateClient(Client client);
}
