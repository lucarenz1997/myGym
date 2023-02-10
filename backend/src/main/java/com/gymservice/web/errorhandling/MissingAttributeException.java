package com.gymservice.web.errorhandling;

public class MissingAttributeException extends RuntimeException {
    public MissingAttributeException(String message) {
        super(message);
    }
}
