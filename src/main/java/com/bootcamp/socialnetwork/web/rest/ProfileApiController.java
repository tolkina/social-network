package com.bootcamp.socialnetwork.web.rest;

import com.bootcamp.socialnetwork.domain.Post;
import com.bootcamp.socialnetwork.service.PostService;
import com.bootcamp.socialnetwork.service.UserService;
import com.bootcamp.socialnetwork.service.dto.PostDto;
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

    @Autowired
    private PostService postService;


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

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("id") Long id) {

        LOGGER.info("Fetching User with id {}", id);

        UserProfileDto user = userService.findProfileById(id);
        if (user == null) {
            LOGGER.error("User with id {} not found.", id);
            return new ResponseEntity<>(new CustomError("User with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // -------------------- Retrieve All Posts of User --------------------

    @RequestMapping(value = "/{id}/post/", method = RequestMethod.GET)
    public ResponseEntity<List<PostDto>> listAllUserPosts(@PathVariable("id") Long id) {

        List<PostDto> posts = postService.findAllByAuthor(id);
        if (posts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    // -------------------- Retrieve Single Post of User  --------------------

    @RequestMapping(value = "/user/{userId}/post/{postId}", method = RequestMethod.GET)
    public ResponseEntity<?> getPost(@PathVariable("userId") Long userId, @PathVariable("postId") Long postId) {

        LOGGER.info("Fetching Post with id {}", postId);

        PostDto post = postService.findAllByIdAndAuthor(postId, userId);
        if (post == null) {
            LOGGER.error("Post with id {} not found.", postId);
            return new ResponseEntity<>(new CustomError("Post with id " + postId
                    + " not found"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(post, HttpStatus.OK);
    }
}
