package com.bootcamp.socialnetwork.service;

import com.bootcamp.socialnetwork.domain.User;

import java.util.List;

public interface UserService {

    User findByUsername(String username);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUserByUsername(String username);

    void deleteAllUsers();

    List<User> findAllUsers();

    boolean isUserExist(User user);
}
