package com.botq.gymsystem.exceptions;

public class UserExerciseExceptionResponse {

    private String exceptionMessage;

    public UserExerciseExceptionResponse(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
}
