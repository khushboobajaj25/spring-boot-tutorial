package com.example.bootlearning.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bootlearning.model.User;
import com.example.bootlearning.service.UserService;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @PostMapping("/users")
    public boolean saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @DeleteMapping("/users/{id}")
    public boolean deleteUserById(@PathVariable Integer id) {
        return userService.deleteUserById(id);
    }

    // This is very specific to this controller. Moved to UserExceptionHandler.java file for global handling.

    // @ExceptionHandler(UserNotFoundException.class)
    // public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
    //     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    // }

    // @ExceptionHandler(UserAlreadyExistException.class)
    // public ResponseEntity<String> handleUserAlreadyExistException(UserAlreadyExistException ex) {
    //     return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    // }
}
