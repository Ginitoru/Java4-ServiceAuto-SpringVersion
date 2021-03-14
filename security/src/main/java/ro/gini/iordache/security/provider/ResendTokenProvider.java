package ro.gini.iordache.security.provider;

import com.gini.errors.AccountAlreadyActiveException;
import com.gini.errors.EmailIsNotRegisteredException;
import com.gini.iordache.entity.user.User;
import com.gini.iordache.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import ro.gini.iordache.security.authentication.ResendTokenAuthentication;

import java.util.Optional;


@AllArgsConstructor
@Component
public class ResendTokenProvider implements AuthenticationProvider {


    private final UserService userService;

    //method 1
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {


        var email = authentication.getName();


        User user = userService.findUserWithToken(email)
                                .orElseThrow(() ->new EmailIsNotRegisteredException("Email is not registered"));



       return Optional.of(user)
                        .filter( u -> u.getActivationToken().getActivatedAt() == null )
                        .map(u -> resendTokenAuthentication(u))
                        .orElseThrow(() -> new AccountAlreadyActiveException("Account is already active"));

    }


    //metod 2
    private ResendTokenAuthentication resendTokenAuthentication(User user){
        userService.updateUserToken(user);

        return new ResendTokenAuthentication(user.getEmail(), null, null);
    }



    @Override
    public boolean supports(Class<?> authentication) {
        return ResendTokenAuthentication.class.equals(authentication);
    }
}
