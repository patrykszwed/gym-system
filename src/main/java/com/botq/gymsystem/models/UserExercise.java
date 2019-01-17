package com.botq.gymsystem.models;

import javax.persistence.*;

@Entity
public class UserExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    private Integer repetitions;
    private Integer series;

    // == constructors ==

    public UserExercise(){}

    public UserExercise(User user, Exercise exercise, Integer repetitions, Integer series) {
        this.user = user;
        this.exercise = exercise;
        this.repetitions = repetitions;
        this.series = series;
    }
}
