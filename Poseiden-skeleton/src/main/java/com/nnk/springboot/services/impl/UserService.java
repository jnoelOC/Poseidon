package com.nnk.springboot.services.impl;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.IUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;


@Service
public class UserService implements IUserService {

    public static final Logger logger = LogManager.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByUsername(String username){

        return userRepository.findByUsername(username);
    }

    public User findById(Long id){

        return userRepository.findById(id).get();
    }
    @Transactional//(rollbackFor = { SQLException.class })//(propagation = REQUIRES_NEW)
    public User saveUser(User user){ //} throws SQLException {
        User u1 = null;
        try {
                u1 = userRepository.save(user);
        }
        catch(Exception ex){
            logger.error("Error in saveUser : " + ex.getMessage());
            //throw new SQLException("Throwing exception for 'saving' rollback. Cannot save user.");
        }
        return u1;
    }

    @Transactional
    public void deleteUser(User user){
        userRepository.delete(user);
    }

    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
