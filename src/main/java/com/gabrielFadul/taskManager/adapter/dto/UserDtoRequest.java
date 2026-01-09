package com.gabrielFadul.taskManager.adapter.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Request com os dados necessários para criação de usuário")
public record UserDtoRequest(
        @Schema(example = "username", description = "Nome do usuário")
        @NotBlank(message = "Nome não pode ser vazio.")
        @Size(min=2, max=20, message = "Nome não pode ser menor que 2 caracteres, nem maior que 20.")
        String name,

        @Schema(example = "1234567@", description = "Senha do usuário")
        @NotBlank(message = "Senha não pode ser vazia.")
        @Size(min=8, message = "Senha não pode ter menos que 8 caracteres.")
        String password,

        @Schema(example = "joao@email.com", description = "Email do usuário")
        @Email
        @NotBlank(message = "Email não pode ser vazio.")
        String email
){}
