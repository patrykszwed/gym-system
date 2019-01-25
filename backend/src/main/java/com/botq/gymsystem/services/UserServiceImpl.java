package com.botq.gymsystem.services;

import com.botq.gymsystem.exceptions.UserException;
import com.botq.gymsystem.models.User;
import com.botq.gymsystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveOrUpdateUser(User user) {
        String email = user.getEmail().toLowerCase();

        try{
            user.setEmail(email);
            return userRepository.save(user);
        } catch(Exception e){
            throw new UserException("Username with email '" + email + "' already exists");
        }
    }

    @Override
    public Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserByEmail(String email) {
        User user = userRepository.findByEmail(email.toLowerCase());

        if(user == null)
            throw new UserException("Username with email '" + email.toLowerCase() + "' does not exist");
        return user;
    }

    @Override
    public void deleteUser(String email) {
        User user = userRepository.findByEmail(email.toLowerCase());

        if(user == null)    // there is no such id
            throw new UserException("Username with email '" + email.toLowerCase() + "' does not exist");

        userRepository.delete(user);
    }
}
