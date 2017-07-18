package com.bootcamp.socialnetwork.repositories;

import com.bootcamp.socialnetwork.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    Post findPostById(Long id);
}
