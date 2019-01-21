package com.botq.gymsystem.controllers;

import com.botq.gymsystem.models.User;
import com.botq.gymsystem.models.UserExercise;
import com.botq.gymsystem.services.UserExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/user/exercise")
public class UserExerciseController {

    private final UserExerciseService userExerciseService;

    @Autowired
    public UserExerciseController(UserExerciseService userExerciseService) {
        this.userExerciseService = userExerciseService;
    }

    // todo add validation
    @PostMapping("/{username}/{exerciseId}/{repetitions}/{series}")
    public ResponseEntity<?> addExerciseToUser(@PathVariable String username, @PathVariable String exerciseId, @PathVariable Integer repetitions, @PathVariable Integer series){
        userExerciseService.addExerciseToUser(username, exerciseId, repetitions, series);

        return new ResponseEntity<>("Exercise with id '" + exerciseId.toLowerCase() + "' was successfully added to User with username '" + username.toLowerCase() + "'", HttpStatus.OK);
    }

    // todo add validation
    @GetMapping("/{username}")
    public ResponseEntity<?> findExercisesByUsername(@PathVariable String username){
        Iterable<UserExercise> userExerciseIterable = userExerciseService.findExercisesByUsername(username);
        Set<UserExercise> userExercises = new HashSet<>();
        userExerciseIterable.forEach(userExercises::add);

        return new ResponseEntity<>(userExercises, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAllUserExercises(){
        return new ResponseEntity<>(userExerciseService.findAllUserExercises(), HttpStatus.OK);
    }

    @DeleteMapping("/{userExerciseId}")
    public ResponseEntity<?> deleteUserExercisesByUsername(@PathVariable String userExerciseId){
        userExerciseService.deleteUserExercise(userExerciseId);
        return new ResponseEntity<>("UserExercise with Id '" + userExerciseId + "' was successfully deleted", HttpStatus.OK);
    }
}
