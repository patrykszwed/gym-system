package com.botq.gymsystem.services;

import com.botq.gymsystem.exceptions.ExerciseException;
import com.botq.gymsystem.exceptions.UserException;
import com.botq.gymsystem.models.Exercise;
import com.botq.gymsystem.models.User;
import com.botq.gymsystem.models.UserExercise;
import com.botq.gymsystem.repositories.ExerciseRepository;
import com.botq.gymsystem.repositories.UserExerciseRepository;
import com.botq.gymsystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private UserExerciseRepository userExerciseRepository;
    private ExerciseRepository exerciseRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserExerciseRepository userExerciseRepository, ExerciseRepository exerciseRepository) {
        this.userRepository = userRepository;
        this.userExerciseRepository = userExerciseRepository;
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public User saveOrUpdateUser(User user) {

        String username = user.getUsername().toLowerCase();

        try{
            user.setUsername(username);
            return userRepository.save(user);
        } catch(Exception e){
            throw new UserException("Username '" + username + "' already exists");
        }

    }

    @Override
    public List<User> findAllUsers() {
        List<User> userList = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(userList::add);
        return userList;
    }

    @Override
    public User findUserByUsername(String username) {
        User user = userRepository.findByUsername(username.toLowerCase());

        if(user == null)
            throw new UserException("Username '" + username.toLowerCase() + "' does not exist");
        return user;
    }

    @Override
    public void deleteUser(String username) {
        User user = userRepository.findByUsername(username.toLowerCase());

        if(user == null)    // there is no such id
            throw new UserException("Username '" + username.toLowerCase() + "' does not exist");

        userRepository.delete(user);
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

    @Override
    public Set<UserExercise> findExercisesByUsername(String username) {
        User user = userRepository.findByUsername(username.toLowerCase());
        Iterable<UserExercise> userExerciseIterable = userExerciseRepository.findAllByUser(user);
        Set<UserExercise> userExercises = new HashSet<>();
        userExerciseIterable.forEach(userExercises::add);

        return userExercises;
    }
}
