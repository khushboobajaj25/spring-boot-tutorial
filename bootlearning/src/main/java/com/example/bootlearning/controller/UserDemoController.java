package com.example.bootlearning.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bootlearning.model.User;
import com.example.bootlearning.service.UserService;

@RestController
public class UserDemoController {
    private UserService userService;

    public UserDemoController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users-demo")
    public boolean saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }
}
