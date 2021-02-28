package ro.gini.iordache.security.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import ro.gini.iordache.security.authentication.EmailAuthentication;
import ro.gini.iordache.security.authentication.UserNamePasswordAuthentication;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UsernameAndPasswordFilter extends OncePerRequestFilter {

    private final AuthenticationManager authenticationManager;

    @Autowired
    public UsernameAndPasswordFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {



        var usernameOrEmail = request.getParameter("username");
        var password = request.getParameter("password");



        if(usernameOrEmail.contains("@")){
            Authentication auth = new EmailAuthentication(usernameOrEmail, password);

            auth = authenticationManager.authenticate(auth);
            SecurityContextHolder.getContext().setAuthentication(auth);
            filterChain.doFilter(request, response);



        }else{

            Authentication auth = new UserNamePasswordAuthentication(usernameOrEmail, password);

            auth = authenticationManager.authenticate(auth);
            SecurityContextHolder.getContext().setAuthentication(auth);
            filterChain.doFilter(request, response);


        }





        System.out.println(request.toString());



    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/login-processing");
    }
}
