package com.gabrielFadul.taskManager.task.dto;

import com.gabrielFadul.taskManager.task.enums.StatusTask;

import java.time.LocalDateTime;

public record TaskResponse(
        Long id,
        String title,
        String description,
        StatusTask statusTask,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
