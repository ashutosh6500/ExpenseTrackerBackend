package tech.app.expenseTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.app.expenseTracker.entities.Person;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person,Long> {
    Boolean existsByUsername(String email);
    Optional<Person> findByUsername(String username);

}
