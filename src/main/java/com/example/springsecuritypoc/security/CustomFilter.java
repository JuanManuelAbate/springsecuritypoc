package com.example.springsecuritypoc.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//our custom filter need to be an implementation of Filter(this is part of spring)
//OncePerRequestFilter in his hierarchy "is a" Filter, based on this our CustomFilter
//it's going to be a Filter and do a concrete implementation of the method doFilterInternal.
public class CustomFilter extends OncePerRequestFilter {

    //In this method we filter the request based on our custom logic, and also
    //we set the authentication for the spring security context.
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
        FilterChain filterChain) throws ServletException, IOException {

        //The request need to have a header that represents the security, in this case we
        //call it Http-Security-Header, we obtain the value associated with the header.
        String httpSecurityHeader = httpServletRequest.getHeader("Http-Security-Header");

        //Here we do our custom validation in the value of the Http-Security-Header, in this case
        //we just check that the value is different than null.
        if (httpSecurityHeader != null) {

            //Here we create an instance of UsernamePasswordAuthenticationToken class that has in his parent hierarchy
            //the interface Authentication that is needed by spring to use in the SecurityContext. From now on spring security
            //uses this check who is the authenticated actor.
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(httpSecurityHeader,"", null);
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }

        //Here the chain filter advance to the next filter in the chain.
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
