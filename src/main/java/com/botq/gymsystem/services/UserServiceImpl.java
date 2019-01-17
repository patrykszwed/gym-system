package com.botq.gymsystem.services;

import com.botq.gymsystem.models.User;
import com.botq.gymsystem.exceptions.UserException;
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
    public User saveOrUpdateUser(User user) {

        String username = user.getUsername().toLowerCase();

        try{
            user.setUsername(username);
            return userRepository.save(user);
        } catch(Exception e){
            throw new UserException("Username '" + username + "' already exists");
        }

    }

    @Override
    public List<User> findAllUsers() {
        List<User> userList = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(userList::add);
        return userList;
    }

    @Override
    public User findUserByUsername(String username) {
        User user = userRepository.findByUsername(username.toLowerCase());

        if(user == null)
            throw new UserException("Username '" + username.toLowerCase() + "' does not exist");
        return user;
    }

    @Override
    public void deleteUser(String username) {
        User user = userRepository.findByUsername(username.toLowerCase());

        if(user == null)    // there is no such id
            throw new UserException("Username '" + username.toLowerCase() + "' does not exist");

        userRepository.delete(user);
    }
}
