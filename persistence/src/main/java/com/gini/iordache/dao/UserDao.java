package com.gini.iordache.dao;


import com.gini.iordache.entity.User;

import java.util.Optional;

public interface UserDao {

    void createUser(User user);

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByEmail(String email);

}
