package com.coderscampus.ShelfApp.Services;


import com.coderscampus.ShelfApp.DTO.*;
import com.coderscampus.ShelfApp.Domain.Token;
import com.coderscampus.ShelfApp.Domain.User;
import com.coderscampus.ShelfApp.Repository.TokenRepository;
import com.coderscampus.ShelfApp.Repository.UserRepository;
import com.coderscampus.ShelfApp.Security.Config.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AuthenticationService {

    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    //Saves user to database and return jwt token
    public AuthenticationResponse register(RegisterRequest request) {

        User user =  new User();
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.User);

        User savedUser = userRepository.save(user);

        String jwtToken = jwtService.generateToken(user);
        saveUserToken(savedUser, jwtToken);

        AuthenticationResponse response = new AuthenticationResponse();
        response.setToken(jwtToken);

        return response;

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();

        String jwtToken = jwtService.generateToken(user);

        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);

        AuthenticationResponse response = new AuthenticationResponse();
        response.setToken(jwtToken);

        return response;
    }

    private void revokeAllUserTokens(User user) {
        List<Token> validUserTokens = tokenRepository.findAllValidTokenByUser(user.getUserId());
        if (validUserTokens.isEmpty()) {
            return;
        }
        validUserTokens.forEach(t -> {
            t.setExpired(true);
            t.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    private void saveUserToken(User user, String jwtToken) {
        Token token = new Token();
        token.setUser(user);
        token.setToken(jwtToken);
        token.setTokenType(TokenType.BEARER);
        token.setRevoked(Boolean.FALSE);
        token.setExpired(Boolean.FALSE);
        tokenRepository.save(token);
    }
}
