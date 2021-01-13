package com.nelioalves.cursomc.services.exceptions;

public class DataIntegrityException extends RuntimeException {
    public DataIntegrityException(String message) {
        super(message);
    }
    public DataIntegrityException(String message, Throwable exception) {
        super(message, exception);
    }
}
