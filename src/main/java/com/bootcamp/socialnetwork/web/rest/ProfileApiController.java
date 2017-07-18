package com.bootcamp.socialnetwork.web.rest;

import com.bootcamp.socialnetwork.service.UserService;
import com.bootcamp.socialnetwork.service.dto.UserProfileDto;
import com.bootcamp.socialnetwork.web.rest.errors.CustomError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profile")
public class ProfileApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileApiController.class);

    @Autowired
    private UserService userService;


    // -------------------- Retrieve All Profiles --------------------

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<UserProfileDto>> listAllUsers() {

        List<UserProfileDto> users = userService.findAllProfiles();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // -------------------- Retrieve Single Profiles --------------------

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("username") String username) {

        LOGGER.info("Fetching User with username {}", username);

        UserProfileDto user = userService.findProfileByUsername(username);
        if (user == null) {
            LOGGER.error("User with username {} not found.", username);
            return new ResponseEntity<>(new CustomError("User with username " + username
                    + " not found"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
