package ro.gini.iordache.security.authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class ResendTokenAuthentication extends UsernamePasswordAuthenticationToken {
    public ResendTokenAuthentication(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public ResendTokenAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
