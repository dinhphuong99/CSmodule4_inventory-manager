package com.cg.exception;

public class TitleCategoryExistsException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public TitleCategoryExistsException(String message) {
        super(message);
    }
}
