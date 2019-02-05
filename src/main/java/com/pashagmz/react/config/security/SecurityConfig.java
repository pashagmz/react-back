package com.pashagmz.react.config.security;

import com.pashagmz.react.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Configuration
public class SecurityConfig {

    private static final String ALL_URL = "/**";
    private static final String LOGIN_PAGE_URL = "/login";
    private static final String LOGOUT_URL = "/logout";

    @Configuration
    public static class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        private final UserService userService;
        private final PasswordEncoder passwordEncoder;

        @Autowired
        public WebSecurityConfig(UserService userService, PasswordEncoder passwordEncoder) {
            this.userService = userService;
            this.passwordEncoder = passwordEncoder;
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable();

            http
                    .authorizeRequests()
                    .antMatchers(LOGIN_PAGE_URL).permitAll()
                    .antMatchers(ALL_URL).authenticated()
                    .antMatchers(ALL_URL).hasAuthority("OP_USER_MANAGEMENT_READ")


                    .and()
                        .formLogin()
                        .loginPage(LOGIN_PAGE_URL)
                        .loginProcessingUrl(LOGIN_PAGE_URL)

                    .and()
                        .sessionManagement()
                        .maximumSessions(100)
                        .maxSessionsPreventsLogin(false)
                        .expiredUrl(LOGOUT_URL);
        }
    }

}
