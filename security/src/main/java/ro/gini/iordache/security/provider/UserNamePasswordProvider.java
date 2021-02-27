package ro.gini.iordache.security.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import ro.gini.iordache.security.authentication.UserNamePasswordAuthentication;

public class UserNamePasswordProvider implements AuthenticationProvider {



    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {



        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UserNamePasswordAuthentication.class.equals(aClass);
    }
}
