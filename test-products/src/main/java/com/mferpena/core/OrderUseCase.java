package com.mferpena.core;

import com.mferpena.core.domain.models.UpdateOrder;

public interface OrderUseCase {
    UpdateOrder updateProductQuantityInOrder(Long orderId, Long productId, int newQuantity);
}
