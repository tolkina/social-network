package com.bootcamp.socialnetwork.service;

import com.bootcamp.socialnetwork.domain.Role;
import com.bootcamp.socialnetwork.domain.User;
import com.bootcamp.socialnetwork.repository.UserRepository;
import com.bootcamp.socialnetwork.service.dto.UserDto;
import com.bootcamp.socialnetwork.service.dto.UserProfileDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    // -------------------- Common --------------------

    @Override
    public boolean isEmailUsed(String email) {
        return userRepository.findByEmail(email) != null;
    }


    // -------------------- UserDto --------------------

    @Override
    public UserDto findById(Long id) {
        try {
            return modelMapper.map(userRepository.findById(id), UserDto.class);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void save(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        user.setEnabled(true);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.getRoles().add(new Role(user, "ROLE_USER"));
        userRepository.save(user);
    }

    @Override
    public void update(UserDto userDto) {
        userRepository.save(modelMapper.map(userDto, User.class));
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
    public boolean isExist(UserDto userDto) {
        return findById(userDto.getId()) != null;
    }


    // -------------------- UserProfileDto --------------------

    @Override
    public UserProfileDto findProfileById(Long id) {
        try {
            return modelMapper.map(userRepository.findById(id), UserProfileDto.class);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<UserProfileDto> findAllProfiles() {
        List<UserProfileDto> users = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            users.add(modelMapper.map(user, UserProfileDto.class));
        }
        return users;
    }

    @Override
    public boolean isExist(UserProfileDto userProfileDto) {
        return findById(userProfileDto.getId()) != null;
    }
}
