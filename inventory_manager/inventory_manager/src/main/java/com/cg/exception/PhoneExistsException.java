package com.cg.exception;

public class PhoneExistsException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public PhoneExistsException(String message) {
        super(message);
    }
}
