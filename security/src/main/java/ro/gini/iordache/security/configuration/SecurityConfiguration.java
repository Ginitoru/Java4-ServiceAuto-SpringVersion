package ro.gini.iordache.security.configuration;

import com.gini.iordache.dao.UserDao;
import com.gini.iordache.dao.impl.UserDaoImpl;
import com.gini.iordache.services.UserSecurityService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ro.gini.iordache.security.filter.UsernameAndPasswordFilter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {



    @Bean
    public UsernameAndPasswordFilter usernameAndPasswordFilter(){
        try {
            return new UsernameAndPasswordFilter(authenticationManagerBean());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Exception in: ----------------> AuthenticationManager");
        }

    }


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }

    @Bean
    public UserDetailsService userSecurityService(){
        return new UserSecurityService();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



}
