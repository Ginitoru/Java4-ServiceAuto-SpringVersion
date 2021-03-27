package com.gini.iordache.services.impl.user;

import com.gini.errors.user.UserAlreadyExists;
import com.gini.iordache.dao.iterfaces.TokenDao;
import com.gini.iordache.dao.iterfaces.UserDao;
import com.gini.iordache.entity.user.ActivationToken;
import com.gini.iordache.entity.user.User;
import com.gini.iordache.services.interfaces.UserService;
import lombok.AllArgsConstructor;
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
    private final TokenDao tokenDao;


    //method 1
    @Override
    @Transactional
    public void createUser(User user) {

        Optional<User> user1 = userDao.findUserByUsername(user.getUsername());
        Optional<User> user2 = userDao.findUserByEmail(user.getEmail());

        if(user1.isEmpty() && user2.isEmpty()){

            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            var token = UUID.randomUUID().toString();

            ActivationToken activationToken = createActivationToken(token, user);
            user.setActivationToken(activationToken);


            userDao.createUser(user);
            emailSender.sendEmail(user.getEmail(), user.getUsername(), token);
            return;
        }

        throw new UserAlreadyExists("User already exists");
    }



    //method 2 -> creem Activation Token
    private ActivationToken createActivationToken(String token, User user){

        ActivationToken activationToken = new ActivationToken();
        var createdAt = LocalDateTime.now();
        var expiredAt = LocalDateTime.now().plusMinutes(2);

        activationToken.setToken(token);
        activationToken.setCreatedAt(createdAt);
        activationToken.setExpiredAt(expiredAt);
        activationToken.setUser(user);

        return activationToken;
    }

    @Override
    @Transactional
    public void updateUserToken(User user){

        var token = UUID.randomUUID().toString();


        tokenDao.updateToken(user.getId(),token);
        emailSender.sendEmail(user.getEmail(),user.getUsername(),token);

    }



    @Override
    @Transactional(readOnly = true)
    public Optional<User> findUserWithToken(String email){
        return userDao.findUserWithToken(email);
    }

    @Override
    @Transactional
    public int activateUserAccount(User user){
        return userDao.activateUserAccount(user);

    }


    @Override
    @Transactional
    public User findUseByUsername(String username){

        return userDao.findUserByUsername(username)
                            .orElseThrow(() -> new RuntimeException("User not Found in security context"));
    }





}
