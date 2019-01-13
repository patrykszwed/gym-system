package com.botq.gymsystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST) // response with BAD_REQUEST status every time we throw this exception
public class UserException extends RuntimeException{

    public UserException(String message) {
        super(message);
    }
}
