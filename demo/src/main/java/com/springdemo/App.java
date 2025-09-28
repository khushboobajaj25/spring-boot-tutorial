package com.springdemo;

import java.lang.reflect.Field;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App {
    private PersonService personService;
    private Integer a;

    // @Autowired
    // public App(PersonService person) {
    //     this.personService = person;
    // }

    // @Autowired
    // public App(PersonService person, Integer a) {
    //     this.personService = person;
    //     this.a = a;
    // }

    public static void main(String[] args) throws Exception {
        // ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        // App app = context.getBean(App.class);
        // PersonService person3 = app.personService;
        // System.out.println("Person 2: " + person3.getName() + ", Age: " + person3.getAge());
        // System.out.println("A ka value = " + app.a);

        // context.getBean(ReflectionDemo.class).call();
    }

    public static void main1(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        // // PersonService person1 = context.getBean("person1", PersonService.class);
        // PersonService person2 = context.getBean("person2", PersonService.class);
        // App app = context.getBean(App.class);
        // PersonService person3 = app.personService;
        // Map<String, PersonService> map = context.getBeansOfType(PersonService.class);
        // System.out.println(map.size());
        // System.out.println(map);

        // // System.out.println("Person 1: " + person1.getName() + ", Age: " +
        // // person1.getAge());
        // System.out.println("Person 2: " + person2.getName() + ", Age: " + person2.getAge());
        // System.out.println("Person 3: " + person3.getName() + ", Age: " + person3.getAge());
        // context.close();
    }
}
