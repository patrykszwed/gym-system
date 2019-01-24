package com.botq.gymsystem.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.*;

@Entity
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Exercise identifier is required")
    @Column(updatable = false, unique = true)
    private String exerciseId;
    @NotBlank(message = "Exercise name is required")
    private String exerciseName;
    private String description;

    @OneToMany(mappedBy = "exercise")
    @JsonIgnore
    private Set<UserExercise> userExercises = new HashSet<>();

    // == constructors ==

    public Exercise() {
    }

    public Exercise(@NotBlank(message = "Exercise identifier is required") String exerciseId, @NotBlank(message = "Exercise name is required") String exerciseName, String description) {
        this.exerciseId = exerciseId;
        this.exerciseName = exerciseName;
        this.description = description;
    }

    // == equals(), hashCode() and toString() ==

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exercise exercise = (Exercise) o;
        return id.equals(exercise.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "id=" + id +
                ", exerciseId='" + exerciseId + '\'' +
                ", exerciseName='" + exerciseName + '\'' +
                ", description='" + description + '\'' +
                ", userExercises=" + userExercises +
                '}';
    }

    //== GETTERS AND SETTERS ==

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(String exerciseId) {
        this.exerciseId = exerciseId;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<UserExercise> getUserExercises() {
        return userExercises;
    }

    public void setUserExercises(Set<UserExercise> userExercises) {
        this.userExercises = userExercises;
    }
}

