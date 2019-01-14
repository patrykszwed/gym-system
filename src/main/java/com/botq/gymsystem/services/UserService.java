package com.botq.gymsystem.services;

import com.botq.gymsystem.domain.User;

import java.util.List;

public interface UserService {
    User saveOrUpdateUser(User user);
    List<User> findAllUsers();
    User findUserByUsername(String username);
    void deleteUser(String username);
}
