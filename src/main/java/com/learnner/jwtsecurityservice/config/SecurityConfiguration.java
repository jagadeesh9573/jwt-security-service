package com.learnner.jwtsecurityservice.config;

import com.learnner.jwtsecurityservice.service.UserService;
import com.learnner.jwtsecurityservice.utility.JWTUtility;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@Slf4j
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private UserService userService;
    private JWTUtility jwtUtility;
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
         http.csrf()
                 .disable()
                 .authorizeRequests()
                 .antMatchers("/authenticate")
                 .permitAll()
                 .anyRequest()
                 .authenticated();
    }
}