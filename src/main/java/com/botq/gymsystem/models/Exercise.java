package com.botq.gymsystem.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Exercise {

    public Exercise() {
    }

    public Exercise(
            @NotBlank(message = "Exercise identifier is required") String exerciseId,
            @NotBlank(message = "Exercise name is required") String exerciseName,
            String description,
            List<User> userList) {
        this.exerciseId = exerciseId;
        this.exerciseName = exerciseName;
        this.description = description;
        this.userList = userList;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Exercise identifier is required")
    @Column(updatable = false, unique = true)
    private String exerciseId;
    @NotBlank(message = "Exercise name is required")
    private String exerciseName;
    private String description;

    @ManyToMany(cascade = {CascadeType.REFRESH})
    @JoinTable(name = "user_exercise",
            joinColumns = @JoinColumn(name = "exercise_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> userList = new ArrayList<>();

    //todo Is something like Backlog needed? For example for tracking series and repetitions each day.

    @Override
    public String toString() {
        return "Exercise{" +
                "id=" + id +
                ", exerciseId='" + exerciseId + '\'' +
                ", exerciseName='" + exerciseName + '\'' +
                ", description='" + description + '\'' +
                ", userList=" + userList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exercise exercise = (Exercise) o;
        return Objects.equals(id, exercise.id) &&
                Objects.equals(exerciseId, exercise.exerciseId) &&
                Objects.equals(exerciseName, exercise.exerciseName) &&
                Objects.equals(description, exercise.description) &&
                Objects.equals(userList, exercise.userList);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, exerciseId, exerciseName, description, userList);
    }

    //GETTERS AND SETTERS

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

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}

