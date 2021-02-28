package ro.gini.iordache.security.provider;

import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.transaction.annotation.Transactional;
import ro.gini.iordache.security.authentication.UserNamePasswordAuthentication;
import ro.gini.iordache.security.service.UserSecurityService;


@Component
public class UserNamePasswordProvider implements AuthenticationProvider {

    private final UserSecurityService userSecurityService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserNamePasswordProvider(UserSecurityService userSecurityService, PasswordEncoder passwordEncoder) {
        this.userSecurityService = userSecurityService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = true) // FARA ADNOTAREA ASTA aici =>(failed to lazily initialize a collection of role) lazy bla bla bla exception si nu merge sa te loghezi => nu trage lista de autorizari desi e fetch=EAGER
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        var usernameOrEmail = authentication.getName();
        var password = authentication.getCredentials().toString();

        UserDetails user = userSecurityService.loadUserByUsername(usernameOrEmail);

        if(passwordEncoder.matches(password, user.getPassword())){
            return new UserNamePasswordAuthentication(usernameOrEmail, password, user.getAuthorities());
        }

        throw new BadCredentialsException("Bad Credential Exception");
    }



    @Override
    public boolean supports(Class<?> aClass) {
        return UserNamePasswordAuthentication.class.equals(aClass);
    }
}
