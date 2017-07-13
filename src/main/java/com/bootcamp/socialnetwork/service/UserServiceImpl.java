package com.bootcamp.socialnetwork.service;

import com.bootcamp.socialnetwork.domain.User;
import com.bootcamp.socialnetwork.repositories.UserRepository;
import com.bootcamp.socialnetwork.service.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;


    public UserDto findByUsername(String username) {
        try {
            return modelMapper.map(userRepository.findByUsername(username), UserDto.class);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public void saveUser(UserDto user) {
        userRepository.save(modelMapper.map(user, User.class));
    }

    public void updateUser(UserDto user) {
        saveUser(user);
    }

    public void deleteUserByUsername(String username) {
        userRepository.delete(username);
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    public List<UserDto> findAllUsers() {
        List<UserDto> users = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            users.add(modelMapper.map(user, UserDto.class));
        }
        return users;
    }

    public boolean isUserExist(UserDto user) {
        return findByUsername(user.getUsername()) != null;
    }
}
