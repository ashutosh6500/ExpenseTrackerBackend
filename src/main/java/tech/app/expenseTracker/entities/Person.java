package tech.app.expenseTracker.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "tbl_users")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Email
    @Column(name = "email")
    private String username;
//
//    @NotBlank
//    @Size(max = 50)
//    @Email
//    @Column(name = "username")
//    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    private Long age;

    public Person(Long id, String name, String password, Long age) {
        this.id = id;
        this.username = name;
        this.password = password;
        this.age = age;
    }
    public Person(){}

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getAge() {
        return this.age;
    }

    public void setAge(Long age) {
        this.age = age;
    }
}
