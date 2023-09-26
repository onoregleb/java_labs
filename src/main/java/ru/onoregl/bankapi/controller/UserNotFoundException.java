package ru.onoregl.bankapi.controller;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message)

    {
        super(message);
    }
}
