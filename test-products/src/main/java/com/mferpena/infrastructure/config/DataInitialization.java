package com.mferpena.infrastructure.config;

import com.mferpena.infrastructure.secondary.persistence.OrderPersistenceImpl;
import com.mferpena.infrastructure.secondary.persistence.entities.OrderEntity;
import com.mferpena.infrastructure.secondary.persistence.entities.ProductEntity;
import com.mferpena.infrastructure.secondary.persistence.repositories.OrderRepository;
import com.mferpena.infrastructure.secondary.persistence.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
@SuppressWarnings("unused")
public class DataInitialization {
    @Bean
    CommandLineRunner initDatabase(OrderRepository orderRepository, ProductRepository productRepository, OrderPersistenceImpl orderPersistence) {
        return args -> {
            List<ProductEntity> products = new ArrayList<>();
            for (int i = 1; i <= 2; i++) {
                ProductEntity product = ProductEntity.builder()
                        .name("Product " + i)
                        .price(10.0 * i)
                        .availableQuantity(100 - i * 10)
                        .build();
                products.add(product);
            }

            OrderEntity order = OrderEntity.builder()
                    .products(new ArrayList<>())
                    .build();

            products.forEach(product -> {
                product.setOrder(order);
                order.getProducts().add(product);
            });

            orderRepository.save(order);

            log.info("Saved orders: {}", orderRepository.findAll().size());
            log.info("Saved Products: {}", productRepository.findAll().size());
        };
    }
}
