package tech.app.expenseTracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.app.expenseTracker.entities.Person;
import tech.app.expenseTracker.repository.PersonRepository;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    PersonRepository userRepository;
    //@Autowired
    //PasswordEncoder passwordEncoder;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
        //return new org.springframework.security.core.userdetails.User(user.getUsername(), passwordEncoder.encode(user.getPassword()), new ArrayList<>());

    }

}

