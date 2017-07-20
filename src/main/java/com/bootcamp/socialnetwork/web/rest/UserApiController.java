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

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("id") Long id) {

        LOGGER.info("Fetching User with id {}", id);

        UserDto userDto = userService.findById(id);
        if (userDto == null) {
            LOGGER.error("User with id {} not found.", id);
            return new ResponseEntity<>(new CustomError("User with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    // -------------------- Create a User --------------------

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto, UriComponentsBuilder ucBuilder) {

        LOGGER.info("Creating User : {}", userDto);

        if (userService.isEmailUsed(userDto.getEmail())) {
            LOGGER.error("Unable to create. A User with email {} already exist", userDto.getEmail());
            return new ResponseEntity<>(new CustomError("Unable to create. A User with email " +
                    userDto.getEmail() + " already exist."), HttpStatus.CONFLICT);
        }
        if (userService.isExist(userDto)) {
            LOGGER.error("Unable to create. A User with id {} already exist", userDto.getId());
            return new ResponseEntity<>(new CustomError("Unable to create. A User with id " +
                    userDto.getId() + " already exist."), HttpStatus.CONFLICT);
        }
        userService.save(userDto);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(userDto.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // -------------------- Update a User --------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody UserDto userDto) {

        LOGGER.info("Updating User with id {}", id);

        if (!userService.isExist(userDto)) {
            // TODO Create if not exist.
            LOGGER.error("Unable to update. User with id {} not found.", id);
            return new ResponseEntity<>(new CustomError("Unable to update. User with id " +
                    id + " not found."), HttpStatus.NOT_FOUND);
        }

        userDto.setFirstName(userDto.getFirstName());
        userDto.setLastName(userDto.getLastName());
        userDto.setEmail(userDto.getEmail());

        userService.update(userDto);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
