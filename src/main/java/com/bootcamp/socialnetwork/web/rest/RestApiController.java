package com.bootcamp.socialnetwork.web.rest;

import com.bootcamp.socialnetwork.domain.User;
import com.bootcamp.socialnetwork.service.UserService;
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

    private static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

    @Autowired
    UserService userService;

    // -------------------Retrieve All Users---------------------------------------------

    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = userService.findAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    // -------------------Retrieve Single User------------------------------------------

    @RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("username") String username) {
        logger.info("Fetching User with username {}", username);
        User user = userService.findByUsername(username);
        if (user == null) {
            logger.error("User with username {} not found.", username);
            return new ResponseEntity(new CustomError("User with username " + username
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    // -------------------Create a User-------------------------------------------

    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        logger.info("Creating User : {}", user);

        if (userService.isUserExist(user)) {
            logger.error("Unable to create. A User with username {} already exist", user.getUsername());
            return new ResponseEntity(new CustomError("Unable to create. A User with username " +
                    user.getUsername() + " already exist."), HttpStatus.CONFLICT);
        }
        userService.saveUser(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/user/{username}").buildAndExpand(user.getUsername()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a User ------------------------------------------------

    @RequestMapping(value = "/user/{username}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@PathVariable("username") String username, @RequestBody User user) {
        logger.info("Updating User with id {}", username);

        User currentUser = userService.findByUsername(username);

        if (currentUser == null) {
            logger.error("Unable to update. User with username {} not found.", username);
            return new ResponseEntity(new CustomError("Unable to update. User with username " +
                    username + " not found."), HttpStatus.NOT_FOUND);
        }

        currentUser.setFirstName(user.getFirstName());
        currentUser.setLastName(user.getLastName());

        userService.updateUser(currentUser);
        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }

    // ------------------- Delete a User-----------------------------------------

    @RequestMapping(value = "/user/{username}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable("username") String username) {
        logger.info("Fetching & Deleting User with username {}", username);

        User user = userService.findByUsername(username);
        if (user == null) {
            logger.error("Unable to delete. User with username {} not found.", username);
            return new ResponseEntity(new CustomError("Unable to delete. User with username " +
                    username + " not found."), HttpStatus.NOT_FOUND);
        }
        userService.deleteUserByUsername(username);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Users-----------------------------

    @RequestMapping(value = "/user/", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteAllUsers() {
        logger.info("Deleting All Users");

        userService.deleteAllUsers();
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
}
