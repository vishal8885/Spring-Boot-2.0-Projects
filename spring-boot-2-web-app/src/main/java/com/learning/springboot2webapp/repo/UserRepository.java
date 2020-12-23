package com.learning.springboot2webapp.repo;

import com.learning.springboot2webapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String Username);
}
