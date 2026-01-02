package tech.app.expenseTracker.entities;

public class PersonModel {
    private String name;
    private String email;
    private String password;
    private Long age;

    public PersonModel(String name, String email, String password, Long age) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
    }
    public PersonModel(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }
}
