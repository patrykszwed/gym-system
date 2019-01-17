package com.botq.gymsystem.exceptions;

public class UserExceptionResponse {

    private String exceptionMessage;

    public UserExceptionResponse(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
}
