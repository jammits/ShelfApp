package com.coderscampus.ShelfApp.Web;

import com.coderscampus.ShelfApp.DTO.AuthenticationRequest;
import com.coderscampus.ShelfApp.DTO.AuthenticationResponse;
import com.coderscampus.ShelfApp.DTO.RegisterRequest;
import com.coderscampus.ShelfApp.Services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;


    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    @ResponseBody
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @GetMapping
    public String getLogin() {
        return "login";
    }

}
