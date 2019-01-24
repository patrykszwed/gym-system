package com.botq.gymsystem.services;

import com.botq.gymsystem.models.Exercise;

public interface ExerciseService {
    Exercise saveOrUpdateExercise(Exercise exercise);
    Iterable<Exercise> findAllExercises();
    Exercise findExerciseByExerciseId(String exerciseId);
    void deleteExercise(String exerciseId);
}
