package com.bootcamp.socialnetwork.repository;

import com.bootcamp.socialnetwork.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    Post findById(Long id);

    Post findByIdAndAuthorId(Long postId, Long authorId);

    List<Post> findAllByAuthorId(Long id);

    List<Post> findAllByOwnerId(Long id);
}
