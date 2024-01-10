package com.example.basebackend.controller;


import com.example.basebackend.dto.request.LoginRequest;
import com.example.basebackend.dto.request.SignupRequest;
import com.example.basebackend.dto.response.JwtResponse;
import com.example.basebackend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@CrossOrigin(origins = "http://localhost:3000", exposedHeaders = {"Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "Access-Control-Allow-Headers"}, allowCredentials = "true", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("/signIn")
    public JwtResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        return authService.signIn(loginRequest, response);
    }

    @PostMapping("/signUp")
    public String registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        return authService.signUp(signUpRequest);
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshToken(@CookieValue("jwtRefresh") String jwtRefresh) {
        return authService.refreshToken(jwtRefresh);
    }

    @PostMapping("/logout/{userId}")
    public String logoutUser(@PathVariable Long userId, HttpServletResponse response) {
        return authService.logoutUser(userId, response);
    }

}
