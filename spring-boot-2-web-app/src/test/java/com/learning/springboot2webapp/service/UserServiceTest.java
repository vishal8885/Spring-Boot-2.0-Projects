package com.learning.springboot2webapp.service;

import com.learning.springboot2webapp.model.User;
import com.learning.springboot2webapp.repo.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
public class UserServiceTest {

    @MockBean
    private UserRepository userRepository;

    private UserService userService;

    @Before
    public void init() {
        this.userService = new UserService(userRepository);
    }

    @Test
    public void loadUserByUsername_HappyPath_ShouldReturn1User() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("pass");
        user.setRole("USER");

        when(userRepository.findByUsername("admin")).thenReturn(user);

        UserDetails actual = userService.loadUserByUsername("admin");

        verify(userRepository, times(1)).findByUsername("admin");
    }
}