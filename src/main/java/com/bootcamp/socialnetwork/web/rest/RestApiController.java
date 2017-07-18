package com.bootcamp.socialnetwork.web.rest;

import com.bootcamp.socialnetwork.service.PostService;
import com.bootcamp.socialnetwork.service.UserService;
import com.bootcamp.socialnetwork.service.dto.PostDto;
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

    @Autowired
    private PostService postService;


    // -------------------- Retrieve All Users --------------------

    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> listAllUsers() {

        List<UserDto> users = userService.findAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // -------------------- Retrieve All Posts --------------------

    @RequestMapping(value = "/post/", method = RequestMethod.GET)
    public ResponseEntity<List<PostDto>> listAllPosts() {
        List<PostDto> posts = postService.findAllPosts();
        if (posts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    // -------------------- Retrieve All User Posts --------------------

    @RequestMapping(value = "/user/{username}/post/", method = RequestMethod.GET)
    public ResponseEntity<List<PostDto>> listAllUserPosts(@PathVariable("username") String username) {
        List<PostDto> posts = postService.findPostsByUsername(username);
        if (posts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    // -------------------- Retrieve Single User --------------------

    @RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("username") String username) {

        LOGGER.info("Fetching User with username {}", username);

        UserDto user = userService.findUserByUsername(username);
        if (user == null) {
            LOGGER.error("User with username {} not found.", username);
            return new ResponseEntity<>(new CustomError("User with username " + username
                    + " not found"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // -------------------- Retrieve Single Post --------------------

    @RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getPost(@PathVariable("id") Long id) {

        LOGGER.info("Fetching Post with id {}", id);

        PostDto post = postService.findPostById(id);
        if (post == null) {
            LOGGER.error("Post with id {} not found.", id);
            return new ResponseEntity<>(new CustomError("Post with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    // -------------------- Retrieve Single User Post --------------------

    @RequestMapping(value = "/user/{username}/post/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getPost(@PathVariable("username") String username, @PathVariable("id") Long id) {

        LOGGER.info("Fetching Post with id {}", id);

        PostDto post = postService.findPostById(id);
        if (post == null || !post.getUsername().equals(username)) {
            LOGGER.error("Post with id {} not found.", id);
            return new ResponseEntity<>(new CustomError("Post with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(post, HttpStatus.OK);
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

    // -------------------- Create a Post --------------------

    @RequestMapping(value = "/post/", method = RequestMethod.POST)
    public ResponseEntity<?> createPost(@RequestBody PostDto post, UriComponentsBuilder ucBuilder) {

        LOGGER.info("Creating Post : {}", post);

        if (postService.isPostExist(post)) {
            LOGGER.error("Unable to create. A Post with id {} already exist", post.getId());
            return new ResponseEntity<>(new CustomError("Unable to create. A Post with id " +
                    post.getId() + " already exist."), HttpStatus.CONFLICT);
        }
        postService.savePost(post);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/post/{id}").buildAndExpand(post.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // -------------------- Update a User --------------------

    @RequestMapping(value = "/user/{username}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@PathVariable("username") String username, @RequestBody UserDto user) {

        LOGGER.info("Updating User with username {}", username);

        UserDto currentUser = userService.findUserByUsername(username);
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

    // -------------------- Update a Post --------------------

    @RequestMapping(value = "/post/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePost(@PathVariable("id") Long id, @RequestBody PostDto post) {

        LOGGER.info("Updating Post with id {}", id);

        PostDto currentPost = postService.findPostById(id);
        if (currentPost == null) {
            LOGGER.error("Unable to update. Post with id {} not found.", id);
            return new ResponseEntity<>(new CustomError("Unable to update. Post with id " +
                    id + " not found."), HttpStatus.NOT_FOUND);
        }

        currentPost.setText(post.getText());
        currentPost.setTime(post.getTime());

        postService.updatePost(currentPost);
        return new ResponseEntity<>(currentPost, HttpStatus.OK);
    }

    // -------------------- Delete a User --------------------

    @RequestMapping(value = "/user/{username}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable("username") String username) {

        LOGGER.info("Fetching & Deleting User with username {}", username);

        UserDto user = userService.findUserByUsername(username);
        if (user == null) {
            LOGGER.error("Unable to delete. User with username {} not found.", username);
            return new ResponseEntity<>(new CustomError("Unable to delete. User with username " +
                    username + " not found."), HttpStatus.NOT_FOUND);
        }

        userService.deleteUserByUsername(username);
        return new ResponseEntity<UserDto>(HttpStatus.NO_CONTENT);
    }

    // -------------------- Delete a Post --------------------

    @RequestMapping(value = "/post/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePost(@PathVariable("id") Long id) {

        LOGGER.info("Fetching & Deleting Post with id {}", id);

        PostDto post = postService.findPostById(id);
        if (post == null) {
            LOGGER.error("Unable to delete. Post with id {} not found.", id);
            return new ResponseEntity<>(new CustomError("Unable to delete. Post with id " +
                    id + " not found."), HttpStatus.NOT_FOUND);
        }

        postService.deletePostById(id);
        return new ResponseEntity<PostDto>(HttpStatus.NO_CONTENT);
    }

    // -------------------- Delete All Users --------------------

    @RequestMapping(value = "/user/", method = RequestMethod.DELETE)
    public ResponseEntity<UserDto> deleteAllUsers() {

        LOGGER.info("Deleting All Users");

        userService.deleteAllUsers();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // -------------------- Delete All Posts --------------------

    @RequestMapping(value = "/post/", method = RequestMethod.DELETE)
    public ResponseEntity<UserDto> deleteAllPosts() {

        LOGGER.info("Deleting All Posts");

        postService.deleteAllPosts();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
