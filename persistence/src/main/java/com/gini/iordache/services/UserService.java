package com.gini.iordache.services;

import com.gini.iordache.entity.User;

public interface UserService {

    void createUser(User user);

    int enableUserAccount(String username, String token);
}
