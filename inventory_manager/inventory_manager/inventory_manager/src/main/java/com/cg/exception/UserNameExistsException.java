package com.cg.exception;

public class UserNameExistsException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public UserNameExistsException(String message) {
        super(message);
    }
}
