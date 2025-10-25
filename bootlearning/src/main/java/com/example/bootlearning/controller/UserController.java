package com.example.bootlearning.controller;

import java.lang.foreign.Linker.Option;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.bootlearning.exception.UserNotFoundException;
import com.example.bootlearning.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class UserController {
    private List<User> users;

    @Autowired
    private UserService userService;

    public static class User {
        public String name;
        public int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    @GetMapping("user")
    public ResponseEntity<?> getUser() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("location", "http://localhost:5000/karan");
        return ResponseEntity.ok(userService.getUser());
    }

    @PostMapping("user")
    public ResponseEntity<?> insertUser(@RequestBody User user) {
        if (user.age < 18) {
            return ResponseEntity.badRequest().body("Age should greater than 18");
        }
        // return ResponseEntity.status(HttpStatus.CREATED).body(user);
        return ResponseEntity.created(URI.create("http://localhost:5000/karan")).build();
    }
    /*
     * 400 - Bad request
     * 404 - Not found
     * 500 - Internal Server error
     * 200 - Success
     * 201 - Created
     * 204 - No content
     * 202 - Accepted
     * 
     * 4xx - Client Error
     * 403 - Forbiden
     * 401 - Unahtorized
     * 409 - Conflict
     * 
     * 
     * 501 - Not Implemented
     * 503 - Service Unavailable
     * 504 - Gateway timeout
     * 
     * Redirectes
     * 301 - Moved permantely
     * 302 - Found
     * 303 - See other
     * 304 - Not Modified
     */
}
