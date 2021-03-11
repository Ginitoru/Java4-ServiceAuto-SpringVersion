package ro.gini.iordache.security.provider;

import com.gini.errors.AccountAlreadyActive;
import com.gini.errors.EmailIsNotRegistered;
import com.gini.errors.InvalidToken;
import com.gini.errors.TokenHasExpired;
import com.gini.iordache.entity.user.ActivationToken;
import com.gini.iordache.entity.user.User;
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
@AllArgsConstructor     //aici cred ca am cam exagerat cu Optional:D
public class TokenProvider implements AuthenticationProvider {

    private final UserServiceImpl userService;

    //method 1
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        var email = authentication.getName();
        var token = authentication.getCredentials().toString();

        User user = userService.findUserWithToken(email)
                                        .orElseThrow(() -> new EmailIsNotRegistered("Email is not registered"));

        ActivationToken userToken = user.getActivationToken();



                    Optional.of(userToken)
                            .filter(t -> t.getActivatedAt() == null)
                            .orElseThrow(() ->new AccountAlreadyActive("Account was already activated"));


                    Optional.of(userToken)
                            .filter(t -> !t.getExpiredAt().isBefore(LocalDateTime.now()))
                            .orElseThrow(() -> new TokenHasExpired("Token has expired"));


                    Optional.of(userToken)
                            .filter(t -> t.getToken().equals(token))
                            .orElseThrow(() -> new InvalidToken("Invalid Token"));


        return Optional.of(user)
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
