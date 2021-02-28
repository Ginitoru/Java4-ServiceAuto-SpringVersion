package com.gini.iordache.services.impl;

import com.gini.iordache.dao.UserDao;
import com.gini.iordache.entity.User;
import com.gini.iordache.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    @Transactional
    public void createUser(User user) {

        Optional<User> user1 = userDao.findUserByUsername(user.getUsername());
        Optional<User> user2 = userDao.findUserByEmail(user.getEmail());

        if(user1.isEmpty() && user2.isEmpty()){
            userDao.createUser(user);

        }else{
            throw new NoSuchElementException("User already exists");
        }
    }
}
