package com.gini.iordache.services;

import com.gini.iordache.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserService {

    void createUser(User user);

  //  int enableUserAccount(String username, String token);

    Optional<User> findUserWithToken(String username);

    @Transactional
    int activateUserAccount(User user);
}
