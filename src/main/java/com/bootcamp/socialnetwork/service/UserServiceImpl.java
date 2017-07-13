package com.bootcamp.socialnetwork.service;

import com.bootcamp.socialnetwork.domain.User;
import com.bootcamp.socialnetwork.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(User user) {
        saveUser(user);
    }

    public void deleteUserByUsername(String username) {
        userRepository.delete(username);
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public boolean isUserExist(User user) {
        return findByUsername(user.getUsername()) != null;
    }
}
