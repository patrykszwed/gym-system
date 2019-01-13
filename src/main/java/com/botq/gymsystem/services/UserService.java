package com.botq.gymsystem.services;

import com.botq.gymsystem.domain.User;

import java.util.List;

public interface UserService {

    List<User> findAllUsers();
    User saveOrUpdateUser(User user);
    User findUserById(Long id);
}
