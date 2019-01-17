package com.botq.gymsystem.controllers;

import com.botq.gymsystem.models.Exercise;
import com.botq.gymsystem.services.ExerciseService;
import com.botq.gymsystem.services.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/exercise")
@CrossOrigin
public class ExerciseController {

    private final ExerciseService exerciseService;
    private final MapValidationErrorService mapValidationErrorService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService, MapValidationErrorService mapValidationErrorService) {
        this.exerciseService = exerciseService;
        this.mapValidationErrorService = mapValidationErrorService;
    }

    @PostMapping("")
    public ResponseEntity<?> createNewExercise(@Valid @RequestBody Exercise exercise, BindingResult result){

        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(result);

        if(errorMap != null)
            return errorMap;

        Exercise tempExercise = exerciseService.saveOrUpdateExercise(exercise);
        return new ResponseEntity<>(tempExercise, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllExercises(){
        return new ResponseEntity<>(exerciseService.findAllExercises(), HttpStatus.OK);
    }

    @GetMapping("/{exerciseId}")
    public ResponseEntity<?> getExerciseByExerciseId(@PathVariable String exerciseId){
        Exercise exercise = exerciseService.findExerciseByExerciseId(exerciseId);

        return new ResponseEntity<>(exercise, HttpStatus.OK);
    }

    @DeleteMapping("/{exerciseId}")
    public ResponseEntity<?> deleteExerciseByExerciseId(@PathVariable String exerciseId){
        exerciseService.deleteExercise(exerciseId);
        return new ResponseEntity<>("Exercise with Id '" + exerciseId + "' was successfully deleted", HttpStatus.OK);
    }
}
