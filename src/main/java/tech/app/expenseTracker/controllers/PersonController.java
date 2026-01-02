package tech.app.expenseTracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.app.expenseTracker.entities.Person;
import tech.app.expenseTracker.entities.PersonModel;
import tech.app.expenseTracker.service.PersonService;

@RestController
@RequestMapping(value = "/api/v1")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("/users/{id}")
    public ResponseEntity<Person> getUser(@PathVariable Long id){
        return new ResponseEntity<>(personService.getUser(id),HttpStatus.OK);
    }
    @PutMapping("/users/{id}")
    public ResponseEntity<Person> update(@RequestBody Person person,@PathVariable Long id){
        return new ResponseEntity<>(personService.updateUser(person, id),HttpStatus.OK);
    }
    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id){
        personService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/getCurrentUser")
    public ResponseEntity<Person> getLoggedinUser(){
        return new ResponseEntity<>(personService.getLoggedInUser(),HttpStatus.OK);
    }
}
