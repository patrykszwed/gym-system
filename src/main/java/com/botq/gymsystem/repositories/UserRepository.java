package com.botq.gymsystem.repositories;

import com.botq.gymsystem.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
