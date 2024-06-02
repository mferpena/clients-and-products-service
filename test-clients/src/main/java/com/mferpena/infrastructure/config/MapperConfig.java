package com.mferpena.infrastructure.config;

import com.mferpena.infrastructure.primary.controllers.mapper.ClientDtoMapper;
import com.mferpena.infrastructure.secondary.persistence.mappers.ClientMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SuppressWarnings("unused")
public class MapperConfig {
    @Bean
    ClientDtoMapper clientDtoMapper() {
        return ClientDtoMapper.INSTANCE;
    }

    @Bean
    ClientMapper clientMapper() {
        return ClientMapper.INSTANCE;
    }
}
