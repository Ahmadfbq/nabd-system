package com.example.userService.repository;

import com.example.userService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)  // Spring: Optimizes transactions for read-only operations
public interface UserRepository extends JpaRepository<User, Long> {

    // Automatically generates a query to find a User by their email
    Optional<User> findByEmail(String email);
}
