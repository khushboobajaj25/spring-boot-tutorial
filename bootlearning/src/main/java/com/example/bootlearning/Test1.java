package com.example.bootlearning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class Test1 {
    @Autowired
    @Qualifier("DocterKhushboo")
    PersonService personService;

    public void print() {
        System.out.println("------------");
        this.personService.printClassName();
    }
}
