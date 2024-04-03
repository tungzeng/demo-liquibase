package com.tungzeng.liquibasedemo.controller;

import com.tungzeng.liquibasedemo.model.Person;
import com.tungzeng.liquibasedemo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("person")
public class Controller {

    @Autowired
    private PersonRepository personRepository;

    @PostMapping("add")
    public String createPerson(@RequestParam String name) {
        personRepository.save(new Person(name, "170"));
        return personRepository.findByName(name) + " saved successfully";
    }

    @GetMapping("person")
    public List<Person> getAllPeople() {
        return (List<Person>) personRepository.findAll();
    }

    @DeleteMapping("delete")
    public String deletePerson(@RequestParam String name) {
//        personRepository.deleteById(id);
//        return "Delete successfully";
        personRepository.deleteByName(name);
        return name + " deleted successfully";
    }

}
