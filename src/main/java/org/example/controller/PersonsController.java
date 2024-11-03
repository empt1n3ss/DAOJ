package org.example.controller;

import org.example.entity.Person;
import org.example.repository.CustomizedPersonsCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class PersonsController {
    private final CustomizedPersonsCrudRepository customizedPersonsCrudRepository;

    @Autowired
    public PersonsController(CustomizedPersonsCrudRepository customizedPersonsCrudRepository) {
        this.customizedPersonsCrudRepository = customizedPersonsCrudRepository;
    }
    @GetMapping("/persons/by-city")
    public List<Person> byCity(@RequestParam String city){
        return customizedPersonsCrudRepository.findByCityOfLiving(city);
    }
    @GetMapping("/persons/by-age")
    public List<Person> byAge(@RequestParam int age){
        return customizedPersonsCrudRepository.findByPersonIdAgeLessThanOrderByPersonIdAgeAsc(age);
    }
    @GetMapping("/persons/by-name-and-surname")
    public Optional<Person> byCity(@RequestParam String name, String surname){
        return customizedPersonsCrudRepository.findByPersonIdNameAndPersonIdSurname(name, surname);
    }
}
