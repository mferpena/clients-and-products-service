package com.mferpena.infrastructure.config;

import com.mferpena.infrastructure.secondary.persistence.mappers.OrderMapper;
import com.mferpena.infrastructure.secondary.persistence.mappers.ProductMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SuppressWarnings("unused")
public class MapperConfig {
    @Bean
    OrderMapper orderMapper() {
        return OrderMapper.INSTANCE;
    }

    @Bean
    ProductMapper productMapper() {
        return ProductMapper.INSTANCE;
    }
}
