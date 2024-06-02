package com.mferpena.core.services;

import com.mferpena.AbstractTest;
import com.mferpena.core.domain.exceptions.ExceededQuantityException;
import com.mferpena.core.domain.exceptions.InvalidQuantityException;
import com.mferpena.core.domain.models.Order;
import com.mferpena.core.domain.models.Product;
import com.mferpena.core.domain.models.UpdateOrder;
import com.mferpena.core.port.OrderPersistencePort;
import com.mferpena.core.port.ProductPersistencePort;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class OrderServiceImplTest extends AbstractTest {
    @Mock
    private OrderPersistencePort orderPersistencePort;
    @Mock
    private ProductPersistencePort productPersistencePort;
    @InjectMocks
    private OrderServiceImpl orderUseCase;

    @Test
    void updateSuccess() throws Exception {
        Long orderId = 1L;
        Long productId = 101L;
        int newQuantity = 5;

        Order mockOrder = convertToObject("/templates/core/services/successfully/order.json", Order.class);

        when(orderPersistencePort.findById(orderId)).thenReturn(Optional.of(mockOrder));

        UpdateOrder updateOrder = orderUseCase.updateProductQuantityInOrder(orderId, productId, newQuantity);

        assertNotNull(updateOrder);
        assertEquals(orderId, updateOrder.getOriginalOrder().getId());
    }

    @Test
    void InvalidQuantityException() {
        assertThrows(InvalidQuantityException.class, () -> orderUseCase.updateProductQuantityInOrder(1L, 1L, -1));
    }

    @Test
    void ExceededQuantityException() throws Exception {
        Long orderId = 1L;
        Long productId = 101L;
        int newQuantity = 100;

        Order mockOrder = convertToObject("/templates/core/services/failed/order.json", Order.class);

        when(orderPersistencePort.findById(orderId)).thenReturn(Optional.of(mockOrder));

        assertThrows(ExceededQuantityException.class, () -> orderUseCase.updateProductQuantityInOrder(orderId, productId, newQuantity));
    }
}