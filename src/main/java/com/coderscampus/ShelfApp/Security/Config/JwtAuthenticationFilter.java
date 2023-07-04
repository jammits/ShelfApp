package com.coderscampus.ShelfApp.Security.Config;


import com.coderscampus.ShelfApp.Repository.TokenRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private TokenRepository tokenRepository;






    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //For the jwt authentication token passed via header
        final String authHeader = request.getHeader("Authorization");
        final String jwToken;
        final String userEmail;


        //Check for jwToken in header and Bearer is the keyword for the token
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        //Checks from position 7 as the token follows the keyword "Bearer " which includes the space
        jwToken = authHeader.substring(7);
        userEmail = jwtService.extractUserName(jwToken); //Extract user email from JWT token

        //Check if user exist and if they exist are they already authenticated
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            Boolean tokenIsValid = tokenRepository.findByToken(jwToken)
                    .map(t -> t.getExpired() != true && t.getRevoked() != true)
                    .orElse(false);
            //check if token is still valid.
            if (jwtService.isTokenValid(jwToken, userDetails) && tokenIsValid) {
                //creating new token
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
