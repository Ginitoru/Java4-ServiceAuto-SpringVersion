package ro.gini.iordache.security.provider;

import com.gini.errors.EmailIsNotRegistered;
import com.gini.iordache.entity.User;
import com.gini.iordache.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import ro.gini.iordache.security.authentication.ResendTokenAuthentication;


@AllArgsConstructor
@Component
public class ResendTokenProvider implements AuthenticationProvider {


    private final UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {


        var email = authentication.getName();


        User user = userService.findUserWithToken(email)
                                .orElseThrow(() ->new EmailIsNotRegistered("Email is not registered"));


        if(user.getActivationToken().getActivatedAt() == null){
            userService.updateUserToken(user);
        }


        return new ResendTokenAuthentication(email, null, null);
    }



    @Override
    public boolean supports(Class<?> authentication) {
        return ResendTokenAuthentication.class.equals(authentication);
    }
}
