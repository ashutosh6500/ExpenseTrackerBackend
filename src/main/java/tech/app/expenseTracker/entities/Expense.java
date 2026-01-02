package tech.app.expenseTracker.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "tbl_expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    private String description;

    private BigDecimal amount;
    private String category;

    @Future
    private Date date;

    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    private Timestamp created_at;

    @UpdateTimestamp
    private Timestamp updated_at;
    public Expense(Long id, String name, String desc, BigDecimal amount, String category, Date date,Timestamp created_at,Timestamp updated_at,Person person) {
        this.id = id;
        this.name = name;
        this.description = desc;
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.person = person;
    }
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "person_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE) //when user is deleted ,all expenses are deleted automatically
    @JsonIgnore
    private Person person;

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public Expense(){}
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Person getPerson() {
        return this.person;
    }

    public void setPerson(Person person) {
        this.person= person;
    }
}
