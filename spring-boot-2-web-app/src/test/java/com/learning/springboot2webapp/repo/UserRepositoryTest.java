package com.learning.springboot2webapp.repo;

import com.learning.springboot2webapp.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByUsername_ShouldgetUser() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("test");
        user.setRole("USER");

        testEntityManager.persist(user);
        testEntityManager.flush();

        User actual = userRepository.findByUsername("admin");
        assertThat(actual).hasFieldOrPropertyWithValue("password","test");

    }

    @Test
    public void save_ShouldSave1User() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("test");
        user.setRole("USER");

        User actual = userRepository.save(user);
        assertThat(actual).isNotNull();
    }
}