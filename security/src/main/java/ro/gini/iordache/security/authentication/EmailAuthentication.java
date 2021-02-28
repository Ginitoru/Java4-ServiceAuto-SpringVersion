package ro.gini.iordache.security.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class EmailAuthentication extends UserNamePasswordAuthentication {
    public EmailAuthentication(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public EmailAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
