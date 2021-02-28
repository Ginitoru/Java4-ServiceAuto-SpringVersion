package ro.gini.iordache.security.provider;

import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import ro.gini.iordache.security.authentication.UserNamePasswordAuthentication;
import ro.gini.iordache.security.service.UserSecurityService;

import java.util.List;

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
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        var usernameOrEmail = authentication.getName();
        var password = authentication.getCredentials().toString();

        System.out.println(usernameOrEmail + password + "  000000000000000");
        UserDetails user = userSecurityService.loadUserByUsername(usernameOrEmail);

        if(passwordEncoder.matches(password, user.getPassword())){


            return new UserNamePasswordAuthentication(usernameOrEmail, password, List.of(() ->"read"));
        }

        throw new BadCredentialsException("Bad Credential Exception");
    }


    @Override
    public boolean supports(Class<?> aClass) {
        return UserNamePasswordAuthentication.class.equals(aClass);
    }
}
