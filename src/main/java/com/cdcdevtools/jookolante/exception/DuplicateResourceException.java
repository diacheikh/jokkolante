package com.cdcdevtools.jookolante.exception;

public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String message) {
        super(message);
    }
    
    public DuplicateResourceException(String resourceName, String field, String value) {
        super(resourceName + " existe déjà avec " + field + ": " + value);
    }
}