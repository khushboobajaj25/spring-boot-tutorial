package com.example.bootlearning;

import org.springframework.stereotype.Component;

@Component("DocterKhushboo")
public class DoctorService implements PersonService {
    public void printClassName() {
        System.out.println("DoctorService hu mein");
    }
}
