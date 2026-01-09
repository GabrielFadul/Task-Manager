package com.gabrielFadul.taskManager.adapter.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Request contendo os dados necessários para criar uma tarefa")
public record TaskCreateRequest(
        @Schema(example = "Andar de carro", description = "Título da tarefa")
        @NotBlank
        @Size(max=20, message = "Titulo não pode ser maior que 20 caracteres")
        String title,

        @Schema(example = "Buscar pessoas as 14hr", description = "Descrição da tarefa")
        @NotBlank
        String description
){}
