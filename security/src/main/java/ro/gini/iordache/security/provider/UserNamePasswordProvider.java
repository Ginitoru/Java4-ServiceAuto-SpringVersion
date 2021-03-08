package ro.gini.iordache.security.provider;

import com.gini.errors.AccountIsNotActive;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import ro.gini.iordache.security.authentication.UserNamePasswordAuthentication;
import ro.gini.iordache.security.service.UserSecurityService;


@Component
@AllArgsConstructor
public class UserNamePasswordProvider implements AuthenticationProvider {

    private final UserSecurityService userSecurityService;
    private final PasswordEncoder passwordEncoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        var usernameOrEmail = authentication.getName();
        var password = authentication.getCredentials().toString();

        UserDetails user = userSecurityService.loadUserByUsername(usernameOrEmail);


        if(passwordEncoder.matches(password, user.getPassword())){

                if(user.isEnabled() && user.isAccountNonLocked()){
                    return new UserNamePasswordAuthentication(usernameOrEmail, password, user.getAuthorities());
                }

                throw new AccountIsNotActive("Account is not activated");
        }



        throw new BadCredentialsException("Bad Credential Exception");
    }



    @Override
    public boolean supports(Class<?> aClass) {
        return UserNamePasswordAuthentication.class.equals(aClass);
    }
}
