package com.bootcamp.socialnetwork.service;

import com.bootcamp.socialnetwork.service.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto findByUsername(String username);

    void saveUser(UserDto user);

    void updateUser(UserDto user);

    void deleteUserByUsername(String username);

    void deleteAllUsers();

    List<UserDto> findAllUsers();

    boolean isUserExist(UserDto user);
}
