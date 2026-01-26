package com.fitness.userServices.exceptions;

public class AlreadyRgisterUserException extends RuntimeException {
    public AlreadyRgisterUserException(String message) {
        super(message);
    }
}
