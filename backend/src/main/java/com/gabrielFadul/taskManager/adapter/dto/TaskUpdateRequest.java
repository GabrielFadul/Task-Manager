package com.gabrielFadul.taskManager.adapter.dto;

import com.gabrielFadul.taskManager.core.enums.StatusTask;

public record TaskUpdateRequest(
        String title,
        String description,
        StatusTask statusTask
) {
}
