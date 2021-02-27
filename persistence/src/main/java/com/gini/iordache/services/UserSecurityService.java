package com.gini.iordache.services;

import com.gini.iordache.dao.UserDao;
import com.gini.iordache.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.gini.iordache.security.securityuser.SecurityUser;

@Service
public class UserSecurityService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {


        //todo: de facut cu regex aici

        if(usernameOrEmail.contains("@")){

           User user = userDao.findUserByUsername(usernameOrEmail)
                    .orElseThrow(() -> new  UsernameNotFoundException("Email not found"));

           return new SecurityUser(user);
        }


        User user = userDao.findUserByEmail(usernameOrEmail)
                    .orElseThrow(() -> new UsernameNotFoundException("Username not found"));


            return new SecurityUser(user);

    }
}
