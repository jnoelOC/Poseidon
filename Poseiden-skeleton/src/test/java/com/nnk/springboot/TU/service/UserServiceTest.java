package com.nnk.springboot.TU.service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.impl.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
//@SpringBootTest
//@AutoConfigureMockMvc
public class UserServiceTest {
    // To be tested
    @InjectMocks
    // remplacer par @Autowired dans TI
    private UserService userService;

    //@Autowired
    //private MockMvc mockMvc;
    @Mock
    private UserRepository userRepository;

    @Test
    @DisplayName("find login in error")
    void FindLoginInError_ShouldReturnFalse() {
        // Arrange
        Boolean ret = true;
        String username = "inconnu";
        // Act
        com.nnk.springboot.domain.User u1 = userService.findByUsername(username);
        if (u1 == null) {
            ret = false;
        }

        // Assert
        assertFalse(ret);
    }

    @Test
    @DisplayName("find login in success")
    void FindLoginInSuccess_ShouldReturnTrue() {
        // Arrange
        Boolean ret = true;
        String username = "user";
        User user = new User(username, "$2a$10$yDzT7pZLVT9oN/V2lF7qL.9bkXMMzLBPZZKcTc/vov89DTj4S/3Ma", "USER");

        when(userRepository.findByUsername(username)).thenReturn(user);
        // Act
        com.nnk.springboot.domain.User u1 = userService.findByUsername(username);
        if (u1 == null) {
            ret = false;
        }
        // Assert
        assertTrue(ret);
    }


}
