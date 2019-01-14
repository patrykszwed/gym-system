package com.botq.gymsystem.services;

import com.botq.gymsystem.domain.User;
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
    public List<User> findAllUsers() {
        List<User> userList = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(userList::add);
        return userList;
    }

    // todo change id to username
    @Override
    public User saveOrUpdateUser(User user) {

        try{
            return userRepository.save(user);
        } catch(Exception e){
            throw new UserException("User with ID " + user.getId() + " already exists");
        }

    }

    @Override
    public User findUserById(Long id) {
        User user = userRepository.findByIdEquals(id);

        if(user == null)
            throw new UserException("User with ID " + id + " does not exist");

        return user;
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findByIdEquals(id);

        if(user == null)    // there is no such id
            throw new UserException("User with ID " + id + " could not be found");

        userRepository.delete(user);
    }
}
