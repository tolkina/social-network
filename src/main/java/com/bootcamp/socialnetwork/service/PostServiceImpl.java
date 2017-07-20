package com.bootcamp.socialnetwork.service;

import com.bootcamp.socialnetwork.domain.Post;
import com.bootcamp.socialnetwork.repository.PostRepository;
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
    public PostDto findById(Long id) {
        try {
            return modelMapper.map(postRepository.findById(id), PostDto.class);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @Override
    public PostDto findAllByIdAndAuthor(Long postId, Long authorId) {
        return modelMapper.map(postRepository.findByIdAndAuthorId(postId, authorId), PostDto.class);
    }

    @Override
    public List<PostDto> findAllByAuthor(Long id) {
        List<PostDto> posts = new ArrayList<>();
        for (Post post : postRepository.findAllByAuthorId(id)) {
            posts.add(modelMapper.map(post, PostDto.class));
        }
        return posts;
    }

    @Override
    public List<PostDto> findAllByOwnerUserId(Long id) {
        List<PostDto> posts = new ArrayList<>();
        for (Post post : postRepository.findAllByOwnerId(id)) {
            posts.add(modelMapper.map(post, PostDto.class));
        }
        return posts;
    }

    @Override
    public void save(PostDto postDto) {
        Post post = modelMapper.map(postDto, Post.class);
        post.setEnabled(true);
        post.setTime(System.currentTimeMillis() / 1000L);
        postRepository.save(post);
    }

    @Override
    public void update(PostDto postDto) {
        save(postDto);
    }

    @Override
    public void deleteById(Long id) {
        postRepository.delete(id);
    }

    @Override
    public void deleteAll() {
        postRepository.deleteAll();
    }

    @Override
    public List<PostDto> findAll() {
        List<PostDto> posts = new ArrayList<>();
        for (Post post : postRepository.findAll()) {
            posts.add(modelMapper.map(post, PostDto.class));
        }
        return posts;
    }

    @Override
    public boolean isExist(PostDto postDto) {
        return findById(postDto.getId()) != null;
    }
}
