package com.example.basebackend.service;



import com.example.basebackend.dto.request.LoginRequest;
import com.example.basebackend.dto.request.SignupRequest;
import com.example.basebackend.dto.response.JwtResponse;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;

public interface AuthService {
    JwtResponse signIn(LoginRequest loginRequest, HttpServletResponse response);

    String signUp(SignupRequest signUpRequest);

    ResponseEntity<?> refreshToken(String jwtRefresh);

    String logoutUser(Long userId, HttpServletResponse response);
}
