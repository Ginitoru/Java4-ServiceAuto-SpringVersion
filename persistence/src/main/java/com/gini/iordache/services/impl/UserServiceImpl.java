package com.gini.iordache.services.impl;

import com.gini.iordache.dao.UserDao;
import com.gini.iordache.entity.User;
import com.gini.iordache.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.gini.iordache.email.sender.EmailSender;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final EmailSender emailSender;



    @Override
    @Transactional()
    public void createUser(User user) {

        Optional<User> user1 = userDao.findUserByUsername(user.getUsername());
        Optional<User> user2 = userDao.findUserByEmail(user.getEmail());

        if(user1.isEmpty() && user2.isEmpty()){

            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);

            userDao.createUser(user);


            var token = UUID.randomUUID().toString();

            //TODO: de vazut cum fac sa baga asta pe un thread separat deoarece imi blocheaza interfacta
            //TODO: interfata grafica pana se conecteaza si trimite mailul
            emailSender.sendEmail(user.getEmail(), user.getUsername(), token);

        }else{
            throw new NoSuchElementException("User already exists");
        }
    }
}
