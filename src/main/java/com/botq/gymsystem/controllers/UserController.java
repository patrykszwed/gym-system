package com.botq.gymsystem.controllers;

import com.botq.gymsystem.models.User;
import com.botq.gymsystem.models.UserExercise;
import com.botq.gymsystem.services.MapValidationErrorService;
import com.botq.gymsystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final MapValidationErrorService mapValidationErrorService;

    @Autowired
    public UserController(UserService userService, MapValidationErrorService mapValidationErrorService) {
        this.userService = userService;
        this.mapValidationErrorService = mapValidationErrorService;
    }

    @PostMapping("")
    public ResponseEntity<?> createNewUser(@Valid @RequestBody User user, BindingResult result){
        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(result);

        if(errorMap != null) // there are errors
            return errorMap;

        User tempUser = userService.saveOrUpdateUser(user);
        return new ResponseEntity<>(tempUser, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public Iterable<User> getUsers(){
        return userService.findAllUsers();
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getUserById(@PathVariable String username){
        User user = userService.findUserByUsername(username);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username){
        userService.deleteUser(username);

        return new ResponseEntity<>("User with username '" + username + "' was successfully deleted", HttpStatus.OK);
    }

    @PostMapping("/exercise/{username}/{exerciseId}/{repetitions}/{series}")
    public ResponseEntity<?> addExerciseToUser(@PathVariable String username, @PathVariable String exerciseId, @PathVariable Integer repetitions, @PathVariable Integer series){
        userService.addExerciseToUser(username, exerciseId, repetitions, series);

        return new ResponseEntity<>("Exercise with id '" + exerciseId.toLowerCase() + "' was successfully added to User with username '" + username.toLowerCase() + "'", HttpStatus.OK);
    }

    @GetMapping("/exercise/{username}")
    public ResponseEntity<?> findExercisesByUsername(@PathVariable String username){
        Iterable<UserExercise> userExerciseIterable = userService.findExercisesByUsername(username);

        return new ResponseEntity<>(userExerciseIterable, HttpStatus.OK);
    }
}
