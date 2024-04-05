package com.tungzeng.liquibasedemo.controller;

import com.tungzeng.liquibasedemo.model.Person;
import com.tungzeng.liquibasedemo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("person")
public class Controller {

    @Autowired
    private PersonRepository personRepository;

    @PostMapping("add")
    public ResponseEntity<?> createPerson(@RequestBody Person person) {

        if(person.getName().isEmpty()) return new ResponseEntity<>("Name can not be empty!" , HttpStatus.BAD_REQUEST);

        personRepository.save(person);

        return new ResponseEntity<>(person, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllPeople() {

        List<Person> list = (List<Person>) personRepository.findAll();

        if(list.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @GetMapping("person/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id) {

        Optional<Person> person = personRepository.findById(id);

        if(!person.isPresent()) return new ResponseEntity<>("User does not exist!" , HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(person, HttpStatus.OK);

    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") int id) {
        try{
            personRepository.deleteById(id);
            return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>("user not found", HttpStatus.NOT_FOUND);
        }

    }

}
