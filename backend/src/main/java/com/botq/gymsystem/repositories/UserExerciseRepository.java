package com.botq.gymsystem.repositories;

import com.botq.gymsystem.models.User;
import com.botq.gymsystem.models.UserExercise;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserExerciseRepository extends CrudRepository<UserExercise, Long> {
    Iterable<UserExercise> findAllByUser(User user);
    Optional<UserExercise> findById(Long id);
}
