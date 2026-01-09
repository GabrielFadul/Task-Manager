package com.gabrielFadul.taskManager.adapter.dto;

import com.gabrielFadul.taskManager.core.enums.StatusTask;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Request contendo os dados necessários para a atualização da tarefa")
public record TaskUpdateRequest(
        @Schema(example = "Andar de carro", description = "Título da tarefa")
        @NotBlank
        @Size(max=20, message = "Titulo não pode ser maior que 20 caracteres")
        String title,

        @Schema(example = "Buscar pessoas as 14hr", description = "Descrição da tarefa")
        @Size(max=20, message = "Titulo não pode ser maior que 20 caracteres")
        String description,

        @Schema(example = "ABERTO")
        StatusTask statusTask
){}
