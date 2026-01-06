package com.gabrielFadul.taskManager.task.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TaskCreateRequest(
        String title,
        String description,
        Long userID
) {}
