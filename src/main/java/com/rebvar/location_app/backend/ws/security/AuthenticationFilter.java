package com.rebvar.location_app.backend.ws.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.rebvar.location_app.backend.ws.AppConstants;
import com.rebvar.location_app.backend.ws.location_app.model.request.UserLoginRequestModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * The Class AuthenticationFilter.
 *
 * @author sehossei
 */
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    
    /** The authentication manager. */
    private final AuthenticationManager authenticationManager;    
    
    /** The content type. */
    private String contentType;
 
    /**
     * Instantiates a new authentication filter.
     *
     * @param authenticationManager the authentication manager
     */
    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
    
    /**
     * Attempt authentication.
     *
     * @param req the req
     * @param res the res
     * @return the authentication
     * @throws AuthenticationException the authentication exception
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
        	
        	contentType = req.getHeader("Accept");
        	
            UserLoginRequestModel creds = new ObjectMapper()
                    .readValue(req.getInputStream(), UserLoginRequestModel.class);
            
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getEmail(),
                            creds.getPassword(),
                            new ArrayList<>())
            );
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    /**
     * Generates the login token, and is added to the response header for future authorization.
     *
     * @param req the req
     * @param res the res
     * @param chain the chain
     * @param auth the auth
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws ServletException the servlet exception
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        
    	
        String userId = ((User) auth.getPrincipal()).getUsername();  
        String token = Jwts.builder()
                .setSubject(userId)
                .setExpiration(new Date(System.currentTimeMillis() + AppConstants.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, AppConstants.TOKEN_SECRET)
                .compact();
        
        res.addHeader(AppConstants.HEADER_STRING, AppConstants.TOKEN_PREFIX + token);
    }  
}
