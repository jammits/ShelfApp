package com.coderscampus.ShelfApp.Services;

import com.coderscampus.ShelfApp.Domain.Token;
import com.coderscampus.ShelfApp.Repository.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
public class LogoutService implements LogoutHandler {

    @Autowired
    private TokenRepository tokenRepository;


    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        final String authHeader = request.getHeader("Authorization");
        final String jwToken;
        //Check for jwToken in header and Bearer is the keyword for the token
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }

        //Checks from position 7 as the token follows the keyword "Bearer " which includes the space
        jwToken = authHeader.substring(7);
        Token storedToken = tokenRepository.findByToken(jwToken).orElse(null);
        if (storedToken != null) {
            storedToken.setExpired(true);
            storedToken.setRevoked(true);
            tokenRepository.save(storedToken);
        }
    }
}
