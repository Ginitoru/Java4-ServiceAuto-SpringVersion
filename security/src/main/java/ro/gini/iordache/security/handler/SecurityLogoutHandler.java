package ro.gini.iordache.security.handler;


import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class SecurityLogoutHandler implements LogoutHandler {





    @Override
    public void logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Authentication authentication) {



    if(authentication != null){

        SecurityContextLogoutHandler context = new SecurityContextLogoutHandler();
        context.logout(request, response, authentication);

    }


    }
}
