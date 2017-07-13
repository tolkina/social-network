package com.bootcamp.socialnetwork.service;

import com.bootcamp.socialnetwork.service.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto findUserByUsername(String username);

    void saveUser(UserDto userDto);

    void updateUser(UserDto userDto);

    void deleteUserByUsername(String username);

    void deleteAllUsers();

    List<UserDto> findAllUsers();

    boolean isUserExist(UserDto userDto);
}
