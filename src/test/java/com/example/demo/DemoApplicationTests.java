package com.example.demo;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import com.example.demo.model.User;
import com.example.demo.model.UserRole;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class DemoApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void contextLoads() {

        // Arrange
        User user = new User();
        user.setRoles(Set.of(UserRole.ADMIN, UserRole.USER));
        userRepository.save(user);

        // Act
        List<User> usersWithRoles = userService.getUsersWithRoles(Arrays.asList(UserRole.USER, UserRole.ADMIN));

        // Assert
        assertEquals(2, usersWithRoles.size());
    }

}
