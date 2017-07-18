package com.bootcamp.socialnetwork.service;

import com.bootcamp.socialnetwork.service.dto.UserDto;
import com.bootcamp.socialnetwork.service.dto.UserProfileDto;

import java.util.List;

public interface UserService {

    // -------------------- UserDto --------------------

    UserDto findByUsername(String username);

    void save(UserDto userDto);

    void update(UserDto userDto);

    List<UserDto> findAll();

    boolean isExist(UserDto userDto);


    // -------------------- UserProfileDto --------------------

    UserProfileDto findProfileByUsername(String username);

    List<UserProfileDto> findAllProfiles();

    boolean isExist(UserProfileDto userProfileDto);
}
