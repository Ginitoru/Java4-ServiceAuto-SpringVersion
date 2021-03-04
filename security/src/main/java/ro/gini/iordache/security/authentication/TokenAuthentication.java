package ro.gini.iordache.security.authentication;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class TokenAuthentication extends UserNamePasswordAuthentication {
    public TokenAuthentication(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public TokenAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
