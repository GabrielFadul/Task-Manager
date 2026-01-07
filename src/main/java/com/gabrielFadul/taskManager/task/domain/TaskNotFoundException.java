package com.gabrielFadul.taskManager.task.domain;

public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException() {
        super("Tarefa não encontrada!");
    }
}
