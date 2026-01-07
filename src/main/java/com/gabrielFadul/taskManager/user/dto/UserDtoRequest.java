package com.gabrielFadul.taskManager.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserDtoRequest(
        @NotBlank(message = "Nome não pode ser vazio.")
        @Size(min=2, max=20, message = "Nome não pode ser menor que 2 caracteres, nem maior que 20.")
        String name,

        @NotBlank(message = "Senha não pode ser vazia.")
        @Size(min=8, message = "Senha não pode ter menos que 8 caracteres.")
        String password,

        @Email
        @NotBlank(message = "Email não pode ser vazio.")
        String email) {
    public void setPassword(String senhaCriptografada) {

    }
}
