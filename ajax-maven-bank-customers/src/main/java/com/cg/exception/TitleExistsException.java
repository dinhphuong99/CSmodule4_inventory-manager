package com.cg.exception;

public class TitleExistsException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public TitleExistsException(String message) {
        super(message);
    }
}
