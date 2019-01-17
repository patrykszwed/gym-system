package com.botq.gymsystem.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class User {

    public User() { }

    public User(
            @NotBlank(message = "Username is required")
            @Size(min = 3, max = 15, message = "Please use from 3 up to 15 characters") String username,
            String firstName,
            String lastName,
            LocalDate registrationDate,
            List<Exercise> exerciseList) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.registrationDate = registrationDate;
        this.exerciseList = exerciseList;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 15, message = "Please use from 3 up to 15 characters")
    @Column(updatable = false, unique = true)
    private String username;

    private String firstName;
    private String lastName;

    @Column(updatable = false)
    private LocalDate registrationDate;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "userList")
    private List<Exercise> exerciseList = new ArrayList<>();

    @PrePersist
    public void onCreate(){
        this.setRegistrationDate(LocalDate.now());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", registrationDate=" + registrationDate +
                ", exerciseList=" + exerciseList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(username, user.username) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(registrationDate, user.registrationDate) &&
                Objects.equals(exerciseList, user.exerciseList);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, username, firstName, lastName, registrationDate, exerciseList);
    }

    //GETTERS AND SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public List<Exercise> getExerciseList() {
        return exerciseList;
    }

    public void setExerciseList(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }
}