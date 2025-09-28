package com.springdemo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springdemo.PersonService;

@Controller
public class HomeController {
    @Autowired
    @Qualifier("person1")
    private PersonService personService1;

    @Autowired
    @Qualifier("person2")
    private PersonService personService2;

    @Autowired
    @Qualifier("person3")
    private PersonService personService3;

    @RequestMapping("/")
    private String home(Model model) {
        model.addAttribute("message", "Hello Khushboo!");
        model.addAttribute("person1", personService1);
        model.addAttribute("person2", personService2);
        model.addAttribute("person3", personService3);
        return "index";
    }
}
