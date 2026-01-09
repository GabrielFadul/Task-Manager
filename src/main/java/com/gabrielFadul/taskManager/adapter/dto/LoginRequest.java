package com.gabrielFadul.taskManager.adapter.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Request contendo os dados necessários para o login")
public record LoginRequest(
        @Schema(example = "joao@email.com", description = "Email do usuário")
        @NotBlank
        @Email
        String email,

        @Schema(example = "1234567@", description = "Senha do usuário")
        @NotBlank
        String password
){}
