package com.example.basebackend.controller;

import com.example.basebackend.payload.request.LogOutRequest;
import com.example.basebackend.payload.request.LoginRequest;
import com.example.basebackend.payload.request.SignupRequest;
import com.example.basebackend.payload.request.TokenRefreshRequest;
import com.example.basebackend.payload.response.JwtResponse;
import com.example.basebackend.payload.response.TokenRefreshResponse;
import com.example.basebackend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

   @Autowired
   private AuthService authService;


   @PostMapping("/signIn")
   public JwtResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
      return authService.signIn(loginRequest);
   }

   @PostMapping("/signUp")
   public String registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
      return authService.signUp(signUpRequest);
   }

   @PostMapping("/refreshToken")
   public TokenRefreshResponse refreshToken(@Valid @RequestBody TokenRefreshRequest request) {
      return authService.refreshToken(request);
   }

   @PostMapping("/logout/{userId}/")
   public void logoutUser(@PathVariable Long userId) {
      authService.logoutUser(userId);
   }

}
