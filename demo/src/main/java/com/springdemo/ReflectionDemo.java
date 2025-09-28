package com.springdemo;

import java.lang.reflect.Field;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ReflectionDemo {
    private App app;

    public ReflectionDemo(App app) {
        this.app = app;
    }

    public void call() throws Exception {
        Class<?> classApp = app.getClass();
        Field fieldA = classApp.getDeclaredField("a");
        fieldA.setAccessible(true);
        System.out.println(fieldA.getType());
        System.out.println(fieldA.get(app));

        Field fieldPerson = classApp.getDeclaredField("personService");
        fieldPerson.setAccessible(true);
        PersonService personService =(PersonService) fieldPerson.get(app);
        System.out.println(personService.getName());
        // fieldA.setAccessible(true);
        // System.out.println(fieldA.getType());
    }
}
