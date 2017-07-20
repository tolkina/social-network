package com.bootcamp.socialnetwork.service;

import com.bootcamp.socialnetwork.service.dto.UserDto;
import com.bootcamp.socialnetwork.service.dto.UserProfileDto;

import java.util.List;

public interface UserService {

    // -------------------- Common --------------------

    boolean isEmailUsed(String email);


    // -------------------- UserDto --------------------

    UserDto findById(Long id);

    void save(UserDto userDto);

    void update(UserDto userDto);

    List<UserDto> findAll();

    boolean isExist(UserDto userDto);


    // -------------------- UserProfileDto --------------------

    UserProfileDto findProfileById(Long id);

    List<UserProfileDto> findAllProfiles();

    boolean isExist(UserProfileDto userProfileDto);
}
