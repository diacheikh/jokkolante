package com.cdcdevtools.jookolante.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
    
    public ResourceNotFoundException(String resourceName, Long id) {
        super(resourceName + " non trouvé avec l'id: " + id);
    }
    
    public ResourceNotFoundException(String resourceName, String field, String value) {
        super(resourceName + " non trouvé avec " + field + ": " + value);
    }
}