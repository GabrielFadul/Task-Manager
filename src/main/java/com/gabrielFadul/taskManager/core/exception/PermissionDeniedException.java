package com.gabrielFadul.taskManager.core.exception;

public class PermissionDeniedException extends RuntimeException {
    public PermissionDeniedException() {
        super("Permissão Negada");
    }
}
