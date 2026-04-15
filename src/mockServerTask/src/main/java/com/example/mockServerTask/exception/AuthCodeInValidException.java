package com.example.mockServerTask.exception;

public class AuthCodeInValidException extends RuntimeException {
    public AuthCodeInValidException(String message) {
        super(message);
    }
}
