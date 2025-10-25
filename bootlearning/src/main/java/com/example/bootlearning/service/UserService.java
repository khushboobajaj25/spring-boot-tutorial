package com.example.bootlearning.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.bootlearning.controller.UserController;
import com.example.bootlearning.exception.UserNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class UserService {
    @Autowired
    private PhoneService phoneService;

    public UserController.User getUser() throws Exception {
        try {
            phoneService.throwError();
        } catch (UserNotFoundException e) {

        }

        return new UserController.User("K", 0);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("errorMessage", ex.getMessage()));
    }
}

