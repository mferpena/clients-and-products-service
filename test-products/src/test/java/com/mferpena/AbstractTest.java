package com.mferpena;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

@ExtendWith(MockitoExtension.class)
public class AbstractTest {
    protected String getJsonFromPath(String pathJson) throws Exception {
        return new String(Files.readAllBytes(Paths.get(Objects.requireNonNull(getClass().getResource(pathJson)).toURI())));
    }

    protected <T> T convertToObject(String pathJson, Class<T> _class) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module());
        mapper.registerModule(new JavaTimeModule());
        return mapper.readValue(getJsonFromPath(pathJson), _class);
    }

    @Configuration
    @SuppressWarnings("unused")
    @ComponentScan(basePackages = {"com.mferpena"})
    static class Config {
    }
}
