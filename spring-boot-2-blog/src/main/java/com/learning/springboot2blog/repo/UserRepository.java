package com.learning.springboot2blog.repo;

import com.learning.springboot2blog.model.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserRepository extends ElasticsearchRepository<User, String> {
    User findByusername(String username);
}
