package com.gini.iordache.services;

import com.gini.iordache.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserService {

    void createUser(User user);

  //  int enableUserAccount(String username, String token);


    void updateUserToken(User user);


    Optional<User> findUserWithToken(String email);


    int activateUserAccount(User user);
}
