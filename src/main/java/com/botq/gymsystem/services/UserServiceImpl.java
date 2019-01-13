package com.botq.gymsystem.services;

import com.botq.gymsystem.domain.User;
import com.botq.gymsystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAllUsers() {
        List<User> userList = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(userList::add);
        return userList;
    }

    // todo add checking if the user already exists
    @Override
    public User saveOrUpdateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findByIdEquals(id);
    }
}
