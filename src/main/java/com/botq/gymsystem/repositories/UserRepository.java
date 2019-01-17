package com.botq.gymsystem.repositories;

import com.botq.gymsystem.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
