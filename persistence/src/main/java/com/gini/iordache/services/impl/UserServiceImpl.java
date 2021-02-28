package com.gini.iordache.services.impl;

import com.gini.iordache.dao.UserDao;
import com.gini.iordache.entity.User;
import com.gini.iordache.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void createUser(User user) {

        Optional<User> user1 = userDao.findUserByUsername(user.getUsername());
        Optional<User> user2 = userDao.findUserByEmail(user.getEmail());

        if(user1.isEmpty() && user2.isEmpty()){

            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);

            userDao.createUser(user);

        }else{
            throw new NoSuchElementException("User already exists");
        }
    }
}
