package com.bootcamp.socialnetwork.service;

import com.bootcamp.socialnetwork.domain.User;

import java.util.List;

public interface UserService {

    User findByUsername(String username);

    void save(User user);

    void update(User user);

    void deleteByUsername(String username);

    void deleteAll();

    List<User> findAll();

    boolean isExist(User user);
}
