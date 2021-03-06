package ro.gini.iordache.security.provider;

import com.gini.errors.AccountAlreadyActive;
import com.gini.errors.TokenHasExpired;
import com.gini.iordache.entity.ActivationToken;
import com.gini.iordache.entity.User;
import com.gini.iordache.services.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import ro.gini.iordache.security.authentication.TokenAuthentication;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@AllArgsConstructor
public class TokenProvider implements AuthenticationProvider {

    private final UserServiceImpl userService;

    //method 1
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        var email = authentication.getName();
        var token = authentication.getCredentials().toString();

        Optional<User> user = userService.findUserWithToken(email);


        ActivationToken tokenFromDatabase = user
                                                .filter(u -> u.getActivationToken().getToken().equals(token))
                                                .map(User::getActivationToken)
                                                .orElseThrow(() -> new IllegalArgumentException("Username or token not found"));


        if(tokenFromDatabase.getActivatedAt() != null){
            throw new AccountAlreadyActive("Account was already activated");
        }


        //todo: to make an option to resend a token so that the account can be activated
        if(tokenFromDatabase.getExpiredAt().isBefore(LocalDateTime.now())){
            throw new TokenHasExpired("Token has expired");
        }


        return user
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