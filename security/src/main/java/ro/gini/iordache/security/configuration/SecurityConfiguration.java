package ro.gini.iordache.security.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import ro.gini.iordache.security.filter.UsernameAndPasswordFilter;
import ro.gini.iordache.security.provider.UserNamePasswordProvider;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserNamePasswordProvider userNamePasswordProvider;

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
        auth.authenticationProvider(userNamePasswordProvider);

    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.addFilterAt(usernameAndPasswordFilter(), BasicAuthenticationFilter.class);
//
//        http.authorizeRequests()
//                .mvcMatchers("/login").permitAll()
//                .mvcMatchers("/").authenticated();
//
//
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterAt(usernameAndPasswordFilter(), BasicAuthenticationFilter.class);


        http.authorizeRequests()
                .mvcMatchers("/").permitAll()
                    .and()
                    .formLogin()
                    .loginPage("/login2").permitAll()

                .and()
                .authorizeRequests()
                        .mvcMatchers("/intra").authenticated();
    }



    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }



}
