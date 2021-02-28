package ro.gini.iordache.security.service;

import com.gini.iordache.dao.UserDao;
import com.gini.iordache.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.gini.iordache.security.securityuser.SecurityUser;

@Service
public class UserSecurityService implements UserDetailsService {

    private final UserDao userDao;

    @Autowired
    public UserSecurityService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {


        //todo: de facut cu regex aici

        if(usernameOrEmail.contains("@")){

           User user = userDao.findUserByEmail(usernameOrEmail)
                    .orElseThrow(() -> new  UsernameNotFoundException("Email not found"));

           return new SecurityUser(user);
        }

        User user = userDao.findUserByUsername(usernameOrEmail)
                    .orElseThrow(() -> new UsernameNotFoundException("Username not found"));


            return new SecurityUser(user);

    }
}
