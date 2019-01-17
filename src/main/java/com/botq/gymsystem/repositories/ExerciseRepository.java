package com.botq.gymsystem.repositories;

import com.botq.gymsystem.models.Exercise;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends CrudRepository<Exercise, Long> {
    Exercise findByExerciseId(String exerciseId);
}
