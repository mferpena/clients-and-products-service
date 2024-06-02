package com.mferpena.infrastructure.primary.controllers;

import com.mferpena.core.OrderUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@SuppressWarnings("unused")
@RequestMapping("api/v1/order")
public class OrderControllers {
    private final OrderUseCase orderUseCase;

    @PatchMapping
    public ResponseEntity<?> updateProductQuantityInOrder(@RequestParam Long orderId, @RequestParam Long productId, @RequestParam int quantity) {
        return ResponseEntity.status(HttpStatus.OK).body(orderUseCase.updateProductQuantityInOrder(orderId, productId, quantity));
    }
}
