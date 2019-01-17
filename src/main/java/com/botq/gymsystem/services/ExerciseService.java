package com.botq.gymsystem.services;

import com.botq.gymsystem.models.Exercise;

import java.util.List;

public interface ExerciseService {
    Exercise saveOrUpdateExercise(Exercise exercise);
    List<Exercise> findAllExercises();
    Exercise findExerciseByExerciseId(String exerciseId);
    void deleteExercise(String exerciseId);
}
