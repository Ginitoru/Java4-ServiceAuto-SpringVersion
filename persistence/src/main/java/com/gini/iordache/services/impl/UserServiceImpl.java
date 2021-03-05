package com.gini.iordache.services.impl;

import com.gini.iordache.dao.UserDao;
import com.gini.iordache.entity.ActivationToken;
import com.gini.iordache.entity.User;
import com.gini.iordache.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.gini.iordache.email.sender.EmailSender;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final EmailSender emailSender;


    //method 1
    @Override
    @Transactional()
    public void createUser(User user) {

        Optional<User> user1 = userDao.findUserByUsername(user.getUsername());
        Optional<User> user2 = userDao.findUserByEmail(user.getEmail());

        if(user1.isEmpty() && user2.isEmpty()){

            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            var token = UUID.randomUUID().toString();

            ActivationToken activationToken = createActivationToken(token);
            user.setActivationToken(activationToken);


            userDao.createUser(user);


            //TODO: de vazut cum fac sa baga asta pe un thread separat deoarece imi blocheaza interfacta
            //TODO: grafica pana se conecteaza si trimite mailul
            emailSender.sendEmail(user.getEmail(), user.getUsername(), token);

        }else{
            throw new NoSuchElementException("User already exists");
        }
    }

    //method 2 -> creem Activation Token
    private ActivationToken createActivationToken(String token){

        ActivationToken activationToken = new ActivationToken();
        var createdAt = LocalDateTime.now();
        var expiredAt = LocalDateTime.now().plusMinutes(2);

        activationToken.setToken(token);
        activationToken.setCreatedAt(createdAt);
        activationToken.setExpiredAt(expiredAt);

        return activationToken;
    }



    @Override
    @Transactional
    public Optional<User> findUserWithToken(String username){
        return userDao.findUserWithToken(username);
    }

    @Override
    @Transactional
    public int activateUserAccount(User user){

        return userDao.activateUserAccount(user);

    }





}
