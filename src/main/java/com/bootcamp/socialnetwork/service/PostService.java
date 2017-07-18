package com.bootcamp.socialnetwork.service;

import com.bootcamp.socialnetwork.domain.Post;
import com.bootcamp.socialnetwork.service.dto.PostDto;

import java.util.List;

public interface PostService {

    PostDto findPostById(Long id);

    List<Post> findPostsByUsername(String username);

    void savePost(PostDto postDto);

    void updatePost(PostDto postDto);

    void deletePostById(Long id);

    void deleteAllPosts();

    List<PostDto> findAllPosts();

    boolean isPostExist(PostDto postDto);
}
