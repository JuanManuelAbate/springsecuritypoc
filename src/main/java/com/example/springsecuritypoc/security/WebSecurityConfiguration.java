package com.example.springsecuritypoc.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity //switch off default security configuration and use custom.
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    //add our custom http security configuration.
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf().disable();

        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.authorizeRequests().antMatchers("/api/insecure").authenticated();
        httpSecurity.authorizeRequests().anyRequest().permitAll();

    }
}
