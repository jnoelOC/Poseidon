package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IUserService {
    User findByUsername(String username);
    List<User> findAll();

    User findById(Long id);

}
