package com.example.bootlearning.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller
 * RestController
 * RequestMapping
 * GetMapping
 * PostMapping
 * PutMapping
 * DeleteMapping
 * PatchMapping
 * PathVariable
 * RequestParam
 * RequestBody
 * ResponseBody
 */
@RestController
// @Controller
// @ResponseBody

public class PhoneController {

    @GetMapping("greet2")
    @PostMapping("greet")
    @Autowired
    // @RequestMapping(value = "greet2", method = RequestMethod.GET)
    public Map<String, ?> greet() {
        return Map.of("message", "Controller working in greet");
    }

    @GetMapping("greet1")
    public String greet1() {
        return "Controller working fine in greet1";
    }

    // @GetMapping("/phone/{name}")
    // public Phone getMethodName(@PathVariable("name") Phone phone) {
    // return new Phone(phone.name, 20, 20);
    // }

    @GetMapping("/phone")
    public Phone getMethodName(Phone phone) {
        return new Phone(phone.name, 20, 20);
    }

    @PostMapping("/phone")
    public Phone getPhone(@RequestBody Phone phone) {
        // return new Phone(phone.name, phone.ram, phone.rom);
        return new Phone(phone.name, phone.ram, phone.rom);
    }
    /**
     * 
     * Phone:
     * name: Karan
     * Rom: 10
     * Ram: 20
     * 
     * name: Realme
     * 
     * name: Realme
     * Rom: 0
     * Ram: 0
     */
}
