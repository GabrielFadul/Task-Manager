package com.gabrielFadul.taskManager.adapter.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resposta contendo os dados da tarefa")
public record UserDtoResponse(
        @Schema(example = "1")
        Long id,

        @Schema(example = "username", description = "Nome do usuário")
        String name,

        @Schema(example = "joao@email.com", description = "Email do usuário")
        String email
){}
