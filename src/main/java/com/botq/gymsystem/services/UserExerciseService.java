package com.botq.gymsystem.services;


import com.botq.gymsystem.models.UserExercise;

import java.util.Set;

public interface UserExerciseService {
    UserExercise addExerciseToUser(String username, String exerciseId, Integer repetitions, Integer series);
    Iterable<UserExercise> findExercisesByUsername(String username);
    Set<UserExercise> findAllUserExercises();
    void deleteUserExercise(String userExerciseId);
}
