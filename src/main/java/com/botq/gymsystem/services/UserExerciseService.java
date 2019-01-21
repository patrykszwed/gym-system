package com.botq.gymsystem.services;


import com.botq.gymsystem.models.UserExercise;

public interface UserExerciseService {
    UserExercise addExerciseToUser(String username, String exerciseId, Integer repetitions, Integer series);
    Iterable<UserExercise> findExercisesByUsername(String username);
}
