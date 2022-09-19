package com.nnk.springboot.services.impl;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Collections;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(@NotNull String username) throws UsernameNotFoundException{

        User user1 = userRepository.findByUsername(username);

        if (user1 != null) {
            String role = user1.getRole();
            return new org.springframework.security.core.userdetails.User(user1.getUsername(), user1.getPassword(),
                        Collections.singletonList(new SimpleGrantedAuthority(role)));
        }
        else //throw new UsernameNotFoundException(userName);
            throw new UsernameNotFoundException("User '" + username + "' was not found in the database.");
    }
}
