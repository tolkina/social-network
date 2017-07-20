package com.bootcamp.socialnetwork.web.rest;

import com.bootcamp.socialnetwork.service.PostService;
import com.bootcamp.socialnetwork.service.dto.PostDto;
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
@RequestMapping("/api/post")
public class PostApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostApiController.class);

    @Autowired
    private PostService postService;


    // -------------------- Retrieve All Posts --------------------

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<PostDto>> listAllPosts() {

        List<PostDto> posts = postService.findAll();
        if (posts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    // -------------------- Retrieve Single Post --------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getPost(@PathVariable("id") Long id) {

        LOGGER.info("Fetching Post with id {}", id);

        PostDto post = postService.findById(id);
        if (post == null) {
            LOGGER.error("Post with id {} not found.", id);
            return new ResponseEntity<>(new CustomError("Post with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    // -------------------- Create a Post --------------------

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> createPost(@RequestBody PostDto post, UriComponentsBuilder ucBuilder) {

        LOGGER.info("Creating Post : {}", post);

        if (postService.isExist(post)) {
            LOGGER.error("Unable to create. A Post with id {} already exist", post.getId());
            return new ResponseEntity<>(new CustomError("Unable to create. A Post with id " +
                    post.getId() + " already exist."), HttpStatus.CONFLICT);
        }
        postService.save(post);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/post/{id}").buildAndExpand(post.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // -------------------- Update a Post --------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePost(@PathVariable("id") Long id, @RequestBody PostDto post) {

        LOGGER.info("Updating Post with id {}", id);

        PostDto currentPost = postService.findById(id);
        if (currentPost == null) {
            LOGGER.error("Unable to update. Post with id {} not found.", id);
            return new ResponseEntity<>(new CustomError("Unable to update. Post with id " +
                    id + " not found."), HttpStatus.NOT_FOUND);
        }

        currentPost.setText(post.getText());

        postService.update(currentPost);
        return new ResponseEntity<>(currentPost, HttpStatus.OK);
    }

    // -------------------- Delete a Post --------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePost(@PathVariable("id") Long id) {

        LOGGER.info("Fetching & Deleting Post with id {}", id);

        PostDto post = postService.findById(id);
        if (post == null) {
            LOGGER.error("Unable to delete. Post with id {} not found.", id);
            return new ResponseEntity<>(new CustomError("Unable to delete. Post with id " +
                    id + " not found."), HttpStatus.NOT_FOUND);
        }

        postService.deleteById(id);
        return new ResponseEntity<PostDto>(HttpStatus.NO_CONTENT);
    }

    // -------------------- Delete All Posts --------------------

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public ResponseEntity<PostDto> deleteAllPosts() {

        LOGGER.info("Deleting All Posts");

        postService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
