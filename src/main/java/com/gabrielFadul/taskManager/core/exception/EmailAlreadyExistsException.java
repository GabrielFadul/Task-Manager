package com.gabrielFadul.taskManager.core.exception;

public class EmailAlreadyExistsException extends RuntimeException{

    public EmailAlreadyExistsException(String email){
        super("Email já cadastrado no sistema " + email + ". Por gentileza tente novamente");
    }
}
