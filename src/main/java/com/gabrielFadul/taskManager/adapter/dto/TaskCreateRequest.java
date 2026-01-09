package com.gabrielFadul.taskManager.adapter.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TaskCreateRequest(
        @NotBlank
        @Size(max=20, message = "Titulo não pode ser maior que 20 caracteres")
        String title,
        @NotBlank
        String description
) {}
