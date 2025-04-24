package com.github.phanison898.cloudbox.repository;

import com.github.phanison898.cloudbox.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Check if a user with the given username already exists
    boolean existsByUsername(String username);

    // find a user by username
    User findByUsername(String username);
}