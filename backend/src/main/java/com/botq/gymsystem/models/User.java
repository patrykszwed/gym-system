package com.botq.gymsystem.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Email is required")
    @Email(message = "Email is not valid!")
    @Column(updatable = false, unique = true)
    private String email;

    private String firstName;
    private String lastName;

    @Column(updatable = false)
    private LocalDate registrationDate;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<UserExercise> userExercises = new HashSet<>();

    @PrePersist
    public void onCreate(){
        this.setRegistrationDate(LocalDate.now());
    }

    // == constructors ==

    public User() { }

    public User(@NotBlank(message = "Username is required") @Size(min = 3, max = 15, message = "Please use from 3 up to 15 characters") String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // == equals(), hashCode() and toString() ==

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", registrationDate=" + registrationDate +
                ", userExercises=" + userExercises +
                '}';
    }

    // == GETTERS AND SETTERS ==

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Set<UserExercise> getUserExercises() {
        return userExercises;
    }

    public void setUserExercises(Set<UserExercise> userExercises) {
        this.userExercises = userExercises;
    }
}