package ro.gini.iordache.security.provider;

import com.gini.errors.user.AccountIsNotActiveException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ro.gini.iordache.security.authentication.EmailAuthentication;



@Component
@AllArgsConstructor
public class EmailProvider implements AuthenticationProvider {

    private final UserDetailsService userSecurityService;
    private final PasswordEncoder passwordEncoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails user = userSecurityService.loadUserByUsername(email);

        if(passwordEncoder.matches(password, user.getPassword())){

                if(user.isEnabled() && user.isAccountNonLocked()){
                    return new EmailAuthentication(user.getUsername(), password, user.getAuthorities());
                }

                throw new AccountIsNotActiveException("Account is not activated");

        }

        throw new BadCredentialsException("Bad Credential Exception");
    }


    @Override
    public boolean supports(Class<?> aClass) {
        return EmailAuthentication.class.equals(aClass);
    }
}
