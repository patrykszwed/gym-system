package com.botq.gymsystem.services;

import com.botq.gymsystem.models.Exercise;
import com.botq.gymsystem.exceptions.ExerciseException;
import com.botq.gymsystem.repositories.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExerciseServiceImpl implements ExerciseService{

    private final ExerciseRepository exerciseRepository;

    @Autowired
    public ExerciseServiceImpl(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public Exercise saveOrUpdateExercise(Exercise exercise) {
        String exerciseId = exercise.getExerciseId().toLowerCase();

        try{
            exercise.setExerciseId(exerciseId);
            return exerciseRepository.save(exercise);
        } catch(Exception e){
            throw new ExerciseException("Exercise with Id '" + exerciseId + "' already exists");
        }
    }

    @Override
    public List<Exercise> findAllExercises() {
        List<Exercise> exercises = new ArrayList<>();
        exerciseRepository.findAll().iterator().forEachRemaining(exercises::add);
        return exercises;
    }

    @Override
    public Exercise findExerciseByExerciseId(String exerciseId) {
        Exercise exercise = exerciseRepository.findByExerciseId(exerciseId.toLowerCase());

        if(exercise == null)
            throw new ExerciseException("Exercise with Id '" + exerciseId.toLowerCase() + "' does not exist");
        return exercise;
    }

    @Override
    public void deleteExercise(String exerciseId) {
        Exercise exercise = exerciseRepository.findByExerciseId(exerciseId.toLowerCase());

        if(exercise == null)
            throw new ExerciseException("Exercise with Id '" + exerciseId.toLowerCase() + "' does not exist");

        exerciseRepository.delete(exercise);
    }
}
