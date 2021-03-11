package com.gini.iordache.dao;


import com.gini.iordache.entity.user.User;

import java.util.Optional;

public interface UserDao {

    void createUser(User user);

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByEmail(String email);

    int activateUserAccount(User user);

    Optional<User> findUserWithToken(String email);

 //   int updateToken(int email, String token);
}
