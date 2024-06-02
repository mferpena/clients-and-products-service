package com.mferpena.core;

import com.mferpena.core.domain.exceptions.OrderNotFoundException;
import com.mferpena.core.domain.models.Order;

public interface OrderFinderUseCase {
    Order findOrderById(Long orderId) throws OrderNotFoundException;
}
