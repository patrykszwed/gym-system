package com.botq.gymsystem.services;

import com.botq.gymsystem.exceptions.ExerciseException;
import com.botq.gymsystem.exceptions.UserException;
import com.botq.gymsystem.exceptions.UserExerciseException;
import com.botq.gymsystem.models.Exercise;
import com.botq.gymsystem.models.User;
import com.botq.gymsystem.models.UserExercise;
import com.botq.gymsystem.repositories.ExerciseRepository;
import com.botq.gymsystem.repositories.UserExerciseRepository;
import com.botq.gymsystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserExerciseServiceImpl implements UserExerciseService {

    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;
    private final UserExerciseRepository userExerciseRepository;

    @Autowired
    public UserExerciseServiceImpl(UserRepository userRepository, ExerciseRepository exerciseRepository, UserExerciseRepository userExerciseRepository) {
        this.userRepository = userRepository;
        this.exerciseRepository = exerciseRepository;
        this.userExerciseRepository = userExerciseRepository;
    }

    // todo add validation!
    @Override
    public UserExercise addExerciseToUser(String username, String exerciseId, Integer repetitions, Integer series) {
        User user = userRepository.findByUsername(username.toLowerCase());
        Exercise exercise = exerciseRepository.findByExerciseId(exerciseId.toLowerCase());

        if(user == null)
            throw new UserException("Username '" + username.toLowerCase() + "' does not exist");
        if(exercise == null)
            throw new ExerciseException("Exercise with Id '" + exerciseId.toLowerCase() + "' does not exist");

        UserExercise userExercise = new UserExercise(user, exercise, repetitions, series);
        return userExerciseRepository.save(userExercise);
    }

    //todo change in a similar way to findAllUserExercises()
    @Override
    public Set<UserExercise> findExercisesByUsername(String username) {
        User user = userRepository.findByUsername(username.toLowerCase());
        Iterable<UserExercise> userExerciseIterable = userExerciseRepository.findAllByUser(user);

        Set<UserExercise> userExercises = new HashSet<>();
        userExerciseIterable.forEach(userExercises::add);

        return userExercises;
    }

    @Override
    public Iterable<UserExercise> findAllUserExercises() {
        return userExerciseRepository.findAll();
    }

    @Override
    public void deleteUserExercise(String userExerciseId) {
        Optional<UserExercise> userExerciseOptional = userExerciseRepository.findById(Long.parseLong(userExerciseId));
        if(userExerciseOptional.isPresent()){
            UserExercise userExercise = userExerciseOptional.get();
            userExerciseRepository.delete(userExercise);
        } else{
            throw new UserExerciseException("UserExercise with id '" + userExerciseId + "' does not exist");
        }
    }
}
