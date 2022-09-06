package com.nnk.springboot.security;

import com.nnk.springboot.exception.ResourceNotFoundException;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        com.nnk.springboot.domain.User user1 = userRepository.findByUsername(username);
//                .orElseThrow(() ->
//                        new UsernameNotFoundException("User not found with username : " + username)
//                );

        return UserPrincipal.create(user1);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        com.nnk.springboot.domain.User user = userRepository.findById(Math.toIntExact(id)).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", id)
        );

        return UserPrincipal.create(user);
    }
}
