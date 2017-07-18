package com.bootcamp.socialnetwork.service;

import com.bootcamp.socialnetwork.service.dto.UserDto;

import java.util.List;

public interface UserDtoService {

    UserDto findByUsername(String username);

    void save(UserDto user);

    void update(UserDto user);

    void deleteByUsername(String username);

    void deleteAll();

    List<UserDto> findAll();

    boolean isExist(UserDto user);
}
