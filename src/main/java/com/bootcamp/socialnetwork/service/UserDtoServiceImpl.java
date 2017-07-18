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

@Service("userDtoService")
@Transactional
public class UserDtoServiceImpl implements UserDtoService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public UserDto findByUsername(String username) {
        try {
            return modelMapper.map(userRepository.findByUsername(username), UserDto.class);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @Override
    public void save(UserDto user) {
        userRepository.save(modelMapper.map(user, User.class));
    }

    @Override
    public void update(UserDto user) {
        save(user);
    }

    @Override
    public void deleteByUsername(String username) {
        userRepository.delete(username);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public List<UserDto> findAll() {
        List<UserDto> users = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            users.add(modelMapper.map(user, UserDto.class));
        }
        return users;
    }

    @Override
    public boolean isExist(UserDto user) {
        return findByUsername(user.getUsername()) != null;
    }
}
