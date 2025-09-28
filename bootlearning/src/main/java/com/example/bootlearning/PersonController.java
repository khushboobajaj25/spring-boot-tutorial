package com.example.bootlearning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PersonController {
    // This class is dependent on PersonService.
    @Autowired
    @Qualifier("DocterKhushboo")
    PersonService personService;

    private final PersonService personService2;

    private PersonService personService3;

    public PersonController(PersonService ps,
            @Value("${spring.application.name}") String name) {
        this.personService2 = ps;
        System.out.println(name);
    }

    public void func() {
        personService.printClassName();
        personService2.printClassName();
        personService3.printClassName();
    }

    @Autowired
    @Qualifier("DocterKhushboo")
    public void setPersonService3(PersonService ps) {
        this.personService3 = ps;
    }
}