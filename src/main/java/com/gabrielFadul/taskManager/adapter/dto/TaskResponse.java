package com.gabrielFadul.taskManager.adapter.dto;

import com.gabrielFadul.taskManager.core.enums.StatusTask;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Resposta contendo os dados da tarefa")
public record TaskResponse(
        @Schema(example = "1")
        Long id,

        @Schema(example = "Andar de carro", description = "Título da tarefa")
        String title,

        @Schema(example = "Buscar pessoas as 14hr", description = "Descrição da tarefa")
        String description,

        @Schema(example = "ABERTO")
        StatusTask statusTask,

        @Schema(example = "2026-01-06T11:55:47")
        LocalDateTime createdAt,

        @Schema(example = "2026-01-06T11:55:47")
        LocalDateTime updatedAt
){}
