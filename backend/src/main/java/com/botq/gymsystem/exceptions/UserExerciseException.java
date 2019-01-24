package com.botq.gymsystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserExerciseException extends RuntimeException {

    public UserExerciseException(String message) {
        super(message);
    }
}
