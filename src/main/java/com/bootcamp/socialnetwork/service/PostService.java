package com.bootcamp.socialnetwork.service;

import com.bootcamp.socialnetwork.service.dto.PostDto;

import java.util.List;

public interface PostService {

    PostDto findById(Long id);

    PostDto findAllByIdAndAuthor(Long postId, Long authorId);

    List<PostDto> findAllByAuthor(Long id);

    List<PostDto> findAllByOwnerUserId(Long id);

    void save(PostDto postDto);

    void update(PostDto postDto);

    void deleteById(Long id);

    void deleteAll();

    List<PostDto> findAll();

    boolean isExist(PostDto postDto);
}
