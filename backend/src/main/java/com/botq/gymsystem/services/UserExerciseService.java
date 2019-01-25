package com.botq.gymsystem.services;


import com.botq.gymsystem.models.UserExercise;

public interface UserExerciseService {
    UserExercise addExerciseToUser(String email, String exerciseId, Integer repetitions, Integer series);
    Iterable<UserExercise> findExercisesByEmail(String email);
    Iterable<UserExercise> findAllUserExercises();
    void deleteUserExercise(String userExerciseId);
}
