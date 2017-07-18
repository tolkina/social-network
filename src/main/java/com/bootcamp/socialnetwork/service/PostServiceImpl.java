package com.bootcamp.socialnetwork.service;

import com.bootcamp.socialnetwork.domain.Post;
import com.bootcamp.socialnetwork.repositories.PostRepository;
import com.bootcamp.socialnetwork.service.dto.PostDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("postService")
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public PostDto findPostById(Long id) {
        try {
            return modelMapper.map(postRepository.findPostById(id), PostDto.class);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    /*@Override
    public List<PostDto> findPostsByUsername(String username) {
        List<PostDto> postDtoList = new ArrayList<>();
        for (Post post : postRepository.findAll()) {
            if (post.getUsername().equals(username)) {
                postDtoList.add(modelMapper.map(post, PostDto.class));
            }
        }
        return postDtoList;
    }*/

    @Override
    public List<Post> findPostsByUsername(String username) {
        return postRepository.findPostsByUsername(username);
    }

    @Override
    public void savePost(PostDto postDto) {
        postRepository.save(modelMapper.map(postDto, Post.class));
    }

    @Override
    public void updatePost(PostDto postDto) {
        savePost(postDto);
    }

    @Override
    public void deletePostById(Long id) {
        postRepository.delete(id);
    }

    @Override
    public void deleteAllPosts() {
        postRepository.deleteAll();
    }

    @Override
    public List<PostDto> findAllPosts() {
        List<PostDto> postDtoList = new ArrayList<>();
        for (Post post : postRepository.findAll()) {
            postDtoList.add(modelMapper.map(post, PostDto.class));
        }
        return postDtoList;
    }

    @Override
    public boolean isPostExist(PostDto postDto) {
        return findPostById(postDto.getId()) != null;
    }
}
