package ro.gini.iordache.security.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import ro.gini.iordache.security.filter.ResendTokenFilter;
import ro.gini.iordache.security.filter.TokenFilter;
import ro.gini.iordache.security.filter.UsernameAndPasswordFilter;
import ro.gini.iordache.security.handler.SecurityLogoutHandler;
import ro.gini.iordache.security.handler.SuccessfulLogoutHandler;
import ro.gini.iordache.security.provider.EmailProvider;
import ro.gini.iordache.security.provider.ResendTokenProvider;
import ro.gini.iordache.security.provider.TokenProvider;
import ro.gini.iordache.security.provider.UserNamePasswordProvider;

@Configuration
@EnableAsync
@EnableAspectJAutoProxy
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private  UserNamePasswordProvider userNamePasswordProvider;
    @Autowired
    private  EmailProvider emailProvider;
    @Autowired
    private  TokenProvider tokenProvider;

    @Autowired
    private ResendTokenProvider resendTokenProvider;

    @Autowired
    private SuccessfulLogoutHandler logoutSuccessHandler;

    @Autowired
    private SecurityLogoutHandler logoutHandler;









    @Bean
    public LogoutFilter logoutFilter(){

        LogoutFilter logoutFilter = new LogoutFilter(logoutSuccessHandler, logoutHandler);
        logoutFilter.setFilterProcessesUrl("/logout3");


        return logoutFilter;
    }


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

    @Bean
    public ResendTokenFilter resendTokenFilter(){

        try {
            return new ResendTokenFilter(authenticationManagerBean());
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
        auth.authenticationProvider(resendTokenProvider);

    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterAt(usernameAndPasswordFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(tokenFilter(),BasicAuthenticationFilter.class)
                .addFilterBefore(resendTokenFilter(), BasicAuthenticationFilter.class)
                .addFilterAt(logoutFilter(), LogoutFilter.class);



        http.authorizeRequests()

                .mvcMatchers("/").permitAll()
                .mvcMatchers("/create-user").permitAll()
                    .and()
                    .formLogin()
                    .loginPage("/login").permitAll()




                .and()
                .authorizeRequests()
                        .mvcMatchers("/main").authenticated()
                        .mvcMatchers("/parts/**").authenticated()
                        .mvcMatchers("/clients/**").authenticated()
                        .mvcMatchers("/vehicles/**").authenticated();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
