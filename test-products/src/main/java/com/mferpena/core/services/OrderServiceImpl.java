package com.mferpena.core.services;

import com.mferpena.core.domain.exceptions.*;
import com.mferpena.core.domain.models.UpdateOrder;
import com.mferpena.core.port.OrderPersistencePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.mferpena.core.OrderFinderUseCase;
import com.mferpena.core.OrderUseCase;
import com.mferpena.core.ProductFinderUseCase;
import com.mferpena.core.domain.models.Order;
import com.mferpena.core.domain.models.Product;
import com.mferpena.core.port.ProductPersistencePort;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderUseCase, OrderFinderUseCase, ProductFinderUseCase {
    private final OrderPersistencePort orderPersistencePort;
    private final ProductPersistencePort productPersistencePort;

    @Override
    public UpdateOrder updateProductQuantityInOrder(Long orderId, Long productId, int newQuantity) {
        if (newQuantity < 0) {
            throw new InvalidQuantityException("The quantity cannot be less than zero.");
        }

        Order order = findOrderById(orderId);
        Product product = getProduct(productId, order);
        Product updateProduct = Product.builder()
                .id(product.getId())
                .price(product.getPrice())
                .name(product.getName())
                .availableQuantity(product.getAvailableQuantity())
                .build();

        if (newQuantity > product.getAvailableQuantity()) {
            throw new ExceededQuantityException("The new quantity exceeds the available quantity of the product.");
        }

        updateProduct.setAvailableQuantity(product.getAvailableQuantity() - newQuantity);

        productPersistencePort.save(updateProduct, order);

        return UpdateOrder.builder()
                .originalOrder(order)
                .updateOrder(findOrderById(orderId))
                .build();
    }

    private static Product getProduct(Long productId, Order order) {
        return order.getProducts().stream()
                .filter(p -> p.getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundInOrderException("Product not found in the order."));
    }

    @Override
    public Order findOrderById(Long orderId) throws OrderNotFoundException {
        return orderPersistencePort.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + orderId));
    }

    @Override
    public Product findProductById(Long productId) throws ProductNotFoundInOrderException {
        return productPersistencePort.findById(productId)
                .orElseThrow(() -> new ProductNotFoundInOrderException("Product not found with ID: " + productId));
    }
}
