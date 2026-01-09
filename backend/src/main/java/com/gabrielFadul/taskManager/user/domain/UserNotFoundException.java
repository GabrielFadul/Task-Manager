package com.gabrielFadul.taskManager.user.domain;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("Usuário não encontrado!");
    }
}
