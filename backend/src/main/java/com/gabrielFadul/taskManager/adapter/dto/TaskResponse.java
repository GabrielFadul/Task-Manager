package com.gabrielFadul.taskManager.adapter.dto;

import com.gabrielFadul.taskManager.core.enums.StatusTask;

import java.time.LocalDateTime;

public record TaskResponse(
        Long id,
        String title,
        String description,
        StatusTask statusTask,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
