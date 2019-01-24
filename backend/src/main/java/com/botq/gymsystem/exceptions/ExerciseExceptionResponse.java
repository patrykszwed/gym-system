package com.botq.gymsystem.exceptions;

public class ExerciseExceptionResponse {

    private String exceptionMessage;

    public ExerciseExceptionResponse(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
}
