package tech.app.expenseTracker.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.app.expenseTracker.entities.LoginModel;
import tech.app.expenseTracker.entities.Person;
import tech.app.expenseTracker.service.PersonService;

@RestController
public class AuthController {

    @Autowired
    private PersonService personService;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<HttpStatus> login(@RequestBody LoginModel loginModel){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginModel.getEmail(),loginModel.getPassword()));
        System.out.println(authentication.toString());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<Person> createUser(@Valid @RequestBody Person person){
        return new ResponseEntity<>(personService.createUser(person), HttpStatus.CREATED);
    }
}
