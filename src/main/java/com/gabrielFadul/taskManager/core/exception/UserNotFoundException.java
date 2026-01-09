package com.gabrielFadul.taskManager.core.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("Usuário não encontrado!");
    }
}
