package com.gabrielFadul.taskManager.core.exception;

public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException() {
        super("Tarefa não encontrada!");
    }
}
