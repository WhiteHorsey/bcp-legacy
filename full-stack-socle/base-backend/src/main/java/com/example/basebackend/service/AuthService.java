package com.example.basebackend.service;

import com.example.basebackend.payload.request.LogOutRequest;
import com.example.basebackend.payload.request.LoginRequest;
import com.example.basebackend.payload.request.SignupRequest;
import com.example.basebackend.payload.request.TokenRefreshRequest;
import com.example.basebackend.payload.response.JwtResponse;
import com.example.basebackend.payload.response.TokenRefreshResponse;

public interface AuthService {
   JwtResponse signIn(LoginRequest loginRequest);
   String signUp(SignupRequest signUpRequest);
   TokenRefreshResponse refreshToken(TokenRefreshRequest refreshRequest);
   void logoutUser(Long userId);
}
