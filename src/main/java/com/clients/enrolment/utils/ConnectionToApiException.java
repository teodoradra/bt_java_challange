package com.clients.enrolment.utils;

public class ConnectionToApiException extends RuntimeException{
    private final String message;

    public ConnectionToApiException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
