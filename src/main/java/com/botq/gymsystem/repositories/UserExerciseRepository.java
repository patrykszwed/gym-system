package com.botq.gymsystem.repositories;

import com.botq.gymsystem.models.User;
import com.botq.gymsystem.models.UserExercise;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserExerciseRepository extends CrudRepository<UserExercise, Long> {
    Iterable<UserExercise> findAllByUser(User user);
}
