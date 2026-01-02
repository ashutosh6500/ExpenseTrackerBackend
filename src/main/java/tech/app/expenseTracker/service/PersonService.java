package tech.app.expenseTracker.service;

import tech.app.expenseTracker.entities.Person;
import tech.app.expenseTracker.entities.PersonModel;

public interface PersonService {
    Person createUser(Person person);
    Person getUser(Long id);
    Person updateUser(Person person,Long id);
    void deleteUser(Long id);

    Person getLoggedInUser();
}
