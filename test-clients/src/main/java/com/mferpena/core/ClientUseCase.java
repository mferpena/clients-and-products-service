package com.mferpena.core;

import com.mferpena.core.domain.models.Client;
import com.mferpena.core.domain.models.CustomerDetail;
import com.mferpena.core.domain.models.UserInfo;

import java.util.List;

public interface ClientUseCase {
    void createClient(Client client);

    List<CustomerDetail> listClient();

    void updateClient(Client client);

    void deleteById(Long id);

    UserInfo getById(Long id);
}
