package com.cg.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class TiteExx  extends RuntimeException{
    public TiteExx(String message) {
        super(message);
    }

}
