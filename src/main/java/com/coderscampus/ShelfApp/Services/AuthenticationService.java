package com.coderscampus.ShelfApp.Services;


import com.coderscampus.ShelfApp.DTO.AuthenticationRequest;
import com.coderscampus.ShelfApp.DTO.AuthenticationResponse;
import com.coderscampus.ShelfApp.DTO.RegisterRequest;
import com.coderscampus.ShelfApp.DTO.Role;
import com.coderscampus.ShelfApp.Domain.User;
import com.coderscampus.ShelfApp.Repository.UserRepository;
import com.coderscampus.ShelfApp.Security.Config.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationService {

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

        userRepository.save(user);

        String jwtToken = jwtService.generateToken(user);
        AuthenticationResponse response = new AuthenticationResponse();
        response.setToken(jwtToken);

        return response;

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();

        String jwtToken = jwtService.generateToken(user);
        AuthenticationResponse response = new AuthenticationResponse();
        response.setToken(jwtToken);

        return response;
    }
}
