package ro.gini.iordache.security.service;

import com.gini.iordache.dao.iterfaces.UserDao;
import com.gini.iordache.entity.user.User;
import lombok.AllArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.gini.iordache.security.securityuser.SecurityUser;

@Service
@AllArgsConstructor
public class UserSecurityService implements UserDetailsService {


    private final UserDao userDao;


    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {


        //todo: de facut cu regex aici -> nu mai cred ca e nevoie deoarece la field email html-ul deja ma obliga sa bag un email corect

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
