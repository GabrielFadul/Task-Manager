package com.gabrielFadul.taskManager.adapter.dto;

import com.gabrielFadul.taskManager.core.enums.StatusTask;
import jakarta.validation.constraints.Size;

public record TaskUpdateRequest(
        String title,
        @Size(max=20, message = "Titulo não pode ser maior que 20 caracteres")
        String description,
        StatusTask statusTask
) {}
