package com.nnk.springboot.TU.service;

import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.impl.MyUserDetailsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class MyUserDetailsServiceTest {
    // To be tested
    @InjectMocks
    private MyUserDetailsService myUserDetailsService;

    @Mock
    private UserRepository userRepository;

    @Test
    @DisplayName("find login in error")
    void FindLoginInError_ShouldReturnFalse() {
        // Arrange
        Boolean ret = true;
        UserDetails userDetails;
        String username = "inconnu";
        // Act
        userDetails = myUserDetailsService.loadUserByUsername(username);
        if (userDetails == null) {
            ret = false;
        }
        // Assert
        assertFalse(ret);
    }

    @Test
    @DisplayName("find login in success")
    void FindLoginInSuccess_ShouldReturnTrue() {
        // Arrange
        Boolean ret = false;
        UserDetails userDetails;
        String username = "user";
        // Act
        userDetails = myUserDetailsService.loadUserByUsername(username);
        if (userDetails != null) {
            ret = true;
        }
        // Assert
        assertTrue(ret);
    }

    @Test
    @DisplayName("find admin login in success")
    void FindAdminLoginInSuccess_ShouldReturnTrue() {
        // Arrange
        Boolean ret = false;
        UserDetails userDetails;
        String username = "admin";
        // Act
        userDetails = myUserDetailsService.loadUserByUsername(username);
        if (userDetails != null) {
            ret = true;
        }
        // Assert
        assertTrue(ret);
    }
}
