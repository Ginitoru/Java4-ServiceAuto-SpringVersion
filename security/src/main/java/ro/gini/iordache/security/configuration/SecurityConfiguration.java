package ro.gini.iordache.security.configuration;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import ro.gini.iordache.security.filter.TokenFilter;
import ro.gini.iordache.security.filter.UsernameAndPasswordFilter;
import ro.gini.iordache.security.provider.EmailProvider;
import ro.gini.iordache.security.provider.TokenProvider;
import ro.gini.iordache.security.provider.UserNamePasswordProvider;

@Configuration

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private  UserNamePasswordProvider userNamePasswordProvider;
    @Autowired
    private  EmailProvider emailProvider;
    @Autowired
    private  TokenProvider tokenProvider;


    @Bean
    public UsernameAndPasswordFilter usernameAndPasswordFilter(){
        try {
            return new UsernameAndPasswordFilter(authenticationManagerBean());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Exception in: ----------------> AuthenticationManager");
        }
    }

    @Bean
    public TokenFilter tokenFilter(){

        try {
            return new TokenFilter(authenticationManagerBean());
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
        auth.authenticationProvider(emailProvider);
        auth.authenticationProvider(tokenProvider);

    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterAt(usernameAndPasswordFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(tokenFilter(),BasicAuthenticationFilter.class);


        http.authorizeRequests()
                .mvcMatchers("/").permitAll()
                .mvcMatchers("/create-user").permitAll()
                    .and()
                    .formLogin()
                    .loginPage("/login2").permitAll()


                .and()
                .authorizeRequests()
                        .mvcMatchers("/intra").authenticated();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
