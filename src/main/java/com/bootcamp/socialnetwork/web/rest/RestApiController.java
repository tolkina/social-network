package com.bootcamp.socialnetwork.web.rest;

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
@RequestMapping("/api")
public class RestApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestApiController.class);

    @Autowired
    private UserService userService;


    // -------------------- Retrieve All Users --------------------

    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> listAllUsers() {

        List<UserDto> users = userService.findAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // -------------------- Retrieve Single User --------------------

    @RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("username") String username) {

        LOGGER.info("Fetching User with username {}", username);

        UserDto user = userService.findByUsername(username);
        if (user == null) {
            LOGGER.error("User with username {} not found.", username);
            return new ResponseEntity<>(new CustomError("User with username " + username
                    + " not found"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // -------------------- Create a User --------------------

    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody UserDto user, UriComponentsBuilder ucBuilder) {

        LOGGER.info("Creating User : {}", user);

        if (userService.isUserExist(user)) {
            LOGGER.error("Unable to create. A User with username {} already exist", user.getUsername());
            return new ResponseEntity<>(new CustomError("Unable to create. A User with username " +
                    user.getUsername() + " already exist."), HttpStatus.CONFLICT);
        }
        userService.saveUser(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/user/{username}").buildAndExpand(user.getUsername()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // -------------------- Update a User --------------------

    @RequestMapping(value = "/user/{username}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@PathVariable("username") String username, @RequestBody UserDto user) {

        LOGGER.info("Updating User with id {}", username);

        UserDto currentUser = userService.findByUsername(username);
        if (currentUser == null) {
            LOGGER.error("Unable to update. User with username {} not found.", username);
            return new ResponseEntity<>(new CustomError("Unable to update. User with username " +
                    username + " not found."), HttpStatus.NOT_FOUND);
        }

        currentUser.setFirstName(user.getFirstName());
        currentUser.setLastName(user.getLastName());

        userService.updateUser(currentUser);
        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }

    // -------------------- Delete a User --------------------

    @RequestMapping(value = "/user/{username}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable("username") String username) {

        LOGGER.info("Fetching & Deleting User with username {}", username);

        UserDto user = userService.findByUsername(username);
        if (user == null) {
            LOGGER.error("Unable to delete. User with username {} not found.", username);
            return new ResponseEntity<>(new CustomError("Unable to delete. User with username " +
                    username + " not found."), HttpStatus.NOT_FOUND);
        }

        userService.deleteUserByUsername(username);
        return new ResponseEntity<UserDto>(HttpStatus.NO_CONTENT);
    }

    // -------------------- Delete All Users --------------------

    @RequestMapping(value = "/user/", method = RequestMethod.DELETE)
    public ResponseEntity<UserDto> deleteAllUsers() {

        LOGGER.info("Deleting All Users");

        userService.deleteAllUsers();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
