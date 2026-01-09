package com.gabrielFadul.taskManager.task.domain;

public class PermissionDeniedException extends RuntimeException {
    public PermissionDeniedException() {
        super("Permissão Negada");
    }
}
