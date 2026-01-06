package com.gabrielFadul.taskManager.task.dto;

import com.gabrielFadul.taskManager.task.enums.StatusTask;

public record TaskUpdateRequest(
        String title,
        String description,
        StatusTask statusTask
) {
}
