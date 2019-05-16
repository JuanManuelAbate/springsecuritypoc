package com.example.springsecuritypoc.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

@Configuration
@EnableWebSecurity //switch off default security configuration and use custom.
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    //add our custom http security configuration.
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf().disable();

        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //check if this endpoint is authenticated.
        httpSecurity.authorizeRequests().antMatchers("/api/secure").authenticated();

        //does not check authentication for any other request.
        httpSecurity.authorizeRequests().anyRequest().permitAll();

        //add our custom filter to the filters chain.
        CustomFilter customFilter = new CustomFilter();
        httpSecurity.addFilterAfter(customFilter, AnonymousAuthenticationFilter.class);

    }
}
