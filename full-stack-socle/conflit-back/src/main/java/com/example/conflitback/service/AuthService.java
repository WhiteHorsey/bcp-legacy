package com.example.conflitback.service;


import com.example.conflitback.dto.request.LoginRequest;
import com.example.conflitback.dto.request.SignupRequest;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;

public interface AuthService {
    ResponseEntity<?> signIn(LoginRequest loginRequest, HttpServletResponse response);

    String signUp(SignupRequest signUpRequest);

    ResponseEntity<?> refreshToken(String jwtRefresh);

    ResponseEntity<?> logoutUser(Long userId, HttpServletResponse response);
}
