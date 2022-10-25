package com.vti.config.authentication;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.vti.entities.User;
import com.vti.services.JWTTokenService;
import com.vti.services.UserService;

public class JWTAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
	
	private UserService userService;
	
    public JWTAuthenticationFilter(String url, AuthenticationManager authManager, UserService userService) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
        this.userService = userService;
    }

    @Override
    public Authentication attemptAuthentication(
    		HttpServletRequest request, 
    		HttpServletResponse response) 
    		throws AuthenticationException, IOException, ServletException {
        
        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                		request.getParameter("email"),
                		request.getParameter("password"),
                        Collections.emptyList()
                )
        );
    }

    @Override
    protected void successfulAuthentication(
    		HttpServletRequest request, 
    		HttpServletResponse response, 
    		FilterChain chain, 
    		Authentication authResult) throws IOException, ServletException {
    	// infor user
    	User user = userService.findUserByEmail4LogIn(authResult.getName());
    	
        JWTTokenService.addJWTTokenAndUserInfoToBody(response, user);
    }
}
