package com.botq.gymsystem.controllers;

import com.botq.gymsystem.models.User;
import com.botq.gymsystem.models.UserExercise;
import com.botq.gymsystem.services.MapValidationErrorService;
import com.botq.gymsystem.services.UserExerciseService;
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

    @GetMapping("/{email}")
    public ResponseEntity<?> getUserById(@PathVariable String email){
        User user = userService.findUserByEmail(email);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable String email){
        userService.deleteUser(email);

        return new ResponseEntity<>("User with email '" + email + "' was successfully deleted", HttpStatus.OK);
    }
}
