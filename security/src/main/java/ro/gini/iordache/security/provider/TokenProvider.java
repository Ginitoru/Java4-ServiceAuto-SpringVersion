package ro.gini.iordache.security.provider;

import com.gini.iordache.entity.User;
import com.gini.iordache.services.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import ro.gini.iordache.security.authentication.TokenAuthentication;

import java.util.Optional;

@Component
@AllArgsConstructor
public class TokenProvider implements AuthenticationProvider {

    private final UserServiceImpl userService;

    //method 1
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        var username = authentication.getName();
        var token = authentication.getCredentials().toString();

        return userService.findUserWithToken(username)
                            .map(this::authenticate)
                            .orElseThrow(() -> new BadCredentialsException("Bad credentials"));

    }

    //method 2
    private Authentication authenticate(User user){

        userService.activateUserAccount(user);
        return new TokenAuthentication(user.getActivationToken().getToken(), null, null);
    }



    @Override
    public boolean supports(Class<?> authentication) {
        return TokenAuthentication.class.equals(authentication);
    }




}
