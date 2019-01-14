package com.botq.gymsystem.repositories;

import com.botq.gymsystem.domain.Exercise;
import org.springframework.data.repository.CrudRepository;

public interface ExerciseRepository extends CrudRepository<Exercise, Long> {
    Exercise findByExerciseId(String exerciseId);
}
