package com.example.bootlearning.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        super("User not found exception");
    }
}
