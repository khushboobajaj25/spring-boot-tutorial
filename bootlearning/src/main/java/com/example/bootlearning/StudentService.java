package com.example.bootlearning;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class StudentService implements PersonService {
    public void printClassName() {
        System.out.println("StudentService hu mein");
    }
}
