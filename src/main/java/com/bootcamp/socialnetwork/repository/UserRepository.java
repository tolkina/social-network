package com.bootcamp.socialnetwork.repository;

import com.bootcamp.socialnetwork.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findById(Long id);

    User findByEmail(String email);
}
