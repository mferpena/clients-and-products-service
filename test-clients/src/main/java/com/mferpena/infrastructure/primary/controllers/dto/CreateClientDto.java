package com.mferpena.infrastructure.primary.controllers.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateClientDto {
    @NotNull(message = "The name cannot be null")
    @NotBlank(message = "The name cannot be empty")
    private String name;
    @NotNull(message = "The paternal surname cannot be null")
    @NotBlank(message = "The paternal surname cannot be empty")
    private String paternalLastName;
    private String maternalLastName;
}
