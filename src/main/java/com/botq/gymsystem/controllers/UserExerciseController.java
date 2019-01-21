package com.botq.gymsystem.controllers;

import com.botq.gymsystem.models.UserExercise;
import com.botq.gymsystem.services.UserExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/exercise")
public class UserExerciseController {

    private final UserExerciseService userExerciseService;

    @Autowired
    public UserExerciseController(UserExerciseService userExerciseService) {
        this.userExerciseService = userExerciseService;
    }

    @PostMapping("/{username}/{exerciseId}/{repetitions}/{series}")
    public ResponseEntity<?> addExerciseToUser(@PathVariable String username, @PathVariable String exerciseId, @PathVariable Integer repetitions, @PathVariable Integer series){
        userExerciseService.addExerciseToUser(username, exerciseId, repetitions, series);

        return new ResponseEntity<>("Exercise with id '" + exerciseId.toLowerCase() + "' was successfully added to User with username '" + username.toLowerCase() + "'", HttpStatus.OK);
    }

    //todo BROKEN @GetMapping for UserExercise
    @GetMapping("/{username}")
    public ResponseEntity<?> findExercisesByUsername(@PathVariable String username){
        Iterable<UserExercise> userExerciseIterable = userExerciseService.findExercisesByUsername(username);

        return new ResponseEntity<>(userExerciseIterable, HttpStatus.OK);
    }
}
