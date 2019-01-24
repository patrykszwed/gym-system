package com.botq.gymsystem.services;

import com.botq.gymsystem.models.User;

public interface UserService {
    User saveOrUpdateUser(User user);
    Iterable<User> findAllUsers();
    User findUserByUsername(String username);
    void deleteUser(String username);
}
