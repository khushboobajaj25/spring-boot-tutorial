package com.example.bootlearning.service;

import org.springframework.stereotype.Component;

import com.example.bootlearning.exception.UserNotFoundException;

@Component
public class PhoneService {
    public void throwError() throws Exception {
        throw new UserNotFoundException();
    }
}
