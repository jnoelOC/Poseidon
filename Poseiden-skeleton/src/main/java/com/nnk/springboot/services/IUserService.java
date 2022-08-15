package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;

public interface IUserService {
    User findByUsername(String username);
}
