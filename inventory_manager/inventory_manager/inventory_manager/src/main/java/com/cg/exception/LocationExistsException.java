package com.cg.exception;

public class LocationExistsException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public LocationExistsException(String message) {
        super(message);
    }
}
