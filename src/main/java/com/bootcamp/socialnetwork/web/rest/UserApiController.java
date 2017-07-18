package com.bootcamp.socialnetwork.web.rest;

import com.bootcamp.socialnetwork.domain.User;
import com.bootcamp.socialnetwork.service.UserService;
import com.bootcamp.socialnetwork.service.dto.UserDto;
import com.bootcamp.socialnetwork.web.rest.errors.CustomError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserApiController.class);

    @Autowired
    private UserService userService;


    // -------------------- Retrieve All Users --------------------

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> listAllUsers() {

        List<UserDto> users = userService.findAll();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // -------------------- Retrieve Single User --------------------

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("username") String username) {

        LOGGER.info("Fetching User with username {}", username);

        UserDto userDto = userService.findByUsername(username);
        if (userDto == null) {
            LOGGER.error("User with username {} not found.", username);
            return new ResponseEntity<>(new CustomError("User with username " + username
                    + " not found"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    // -------------------- Create a User --------------------

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto, UriComponentsBuilder ucBuilder) {

        LOGGER.info("Creating User : {}", userDto);

        if (userService.isExist(userDto)) {
            LOGGER.error("Unable to create. A User with username {} already exist", userDto.getUsername());
            return new ResponseEntity<>(new CustomError("Unable to create. A User with username " +
                    userDto.getUsername() + " already exist."), HttpStatus.CONFLICT);
        }
        userService.save(userDto);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/{username}").buildAndExpand(userDto.getUsername()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // -------------------- Update a User --------------------

    @RequestMapping(value = "/{username}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@PathVariable("username") String username, @RequestBody User user) {

        LOGGER.info("Updating User with id {}", username);

        UserDto userDto = userService.findByUsername(username);
        if (userDto == null) {
            LOGGER.error("Unable to update. User with username {} not found.", username);
            return new ResponseEntity<>(new CustomError("Unable to update. User with username " +
                    username + " not found."), HttpStatus.NOT_FOUND);
        }

        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());

        userService.update(userDto);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
