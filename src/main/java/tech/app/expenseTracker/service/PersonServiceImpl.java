package tech.app.expenseTracker.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.app.expenseTracker.entities.Person;
import tech.app.expenseTracker.entities.PersonModel;
import tech.app.expenseTracker.exceptions.ItemAlreadyExistException;
import tech.app.expenseTracker.exceptions.ResourceNotFoundException;
import tech.app.expenseTracker.repository.PersonRepository;

import java.util.Optional;

@Service
public class PersonServiceImpl implements  PersonService{
    @Autowired
    public PersonRepository personRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Person createUser(Person person){
        for(Person p : personRepository.findAll()){
            if(p.getUsername().equals(person.getUsername())){
                throw new ItemAlreadyExistException("User is already registered by this email:"+person.getUsername());
            }
        }
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        return personRepository.save(person);
    }
    @Override
    public Person getUser(Long id){
        Optional<Person> p = personRepository.findById(id);
        if(p.isPresent()){
            return p.get();
        }
        throw new ResourceNotFoundException("User not found for id");
    }
    @Override
    public Person updateUser(Person person,Long id){
        Optional<Person> p = personRepository.findById(id);
        if(p.isPresent()){
            Person newUser = p.get();
            newUser.setAge(person.getAge() == null ? newUser.getAge() : person.getAge());
            newUser.setUsername(person.getUsername() == null ? newUser.getUsername() : person.getUsername());
            newUser.setUsername(person.getUsername() == null ? newUser.getUsername() : person.getUsername());
            newUser.setPassword(person.getPassword() == null ? newUser.getPassword() : person.getPassword());
            newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
            personRepository.save(newUser);
            return newUser;
        }
        throw  new ResourceNotFoundException("user does not exist");
    }
    @Override
    public void deleteUser(Long id){
       Person user = getUser(id);
       personRepository.delete(user);
    }

    @Override
    public Person getLoggedInUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return personRepository.findByUsername(email).orElseThrow(() -> new UsernameNotFoundException("User not found with given email"));
    }
}
