package com.example.bootlearning.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Integer id) {
        super("User not found with ID: " + id);
    }
}
