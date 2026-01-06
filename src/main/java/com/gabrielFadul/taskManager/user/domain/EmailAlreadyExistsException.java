package com.gabrielFadul.taskManager.user.domain;

public class EmailAlreadyExistsException extends RuntimeException{

    public EmailAlreadyExistsException(String email){
        super("Email já cadastrado no sistema " + email + ". Por gentileza tente novamente");
    }
}
