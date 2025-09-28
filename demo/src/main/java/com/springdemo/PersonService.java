package com.springdemo;

import org.springframework.stereotype.Component;

@Component("person3")
public class PersonService {
    private String name;
    private int age;

    public PersonService() {
        this.name = "John Doe";
        this.age = 30;
    }

    public PersonService(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

}
