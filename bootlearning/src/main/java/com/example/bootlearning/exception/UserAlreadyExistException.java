package com.example.bootlearning.exception;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(Integer id) {
        super("User already exists with ID: " + id);
    }
}
