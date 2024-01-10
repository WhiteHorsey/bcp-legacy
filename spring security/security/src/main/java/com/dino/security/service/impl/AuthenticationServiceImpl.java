package com.dino.security.service.impl;

import com.dino.security.dto.AuthenticationRequest;
import com.dino.security.dto.AuthenticationResponse;
import com.dino.security.dto.RegisterRequest;
import com.dino.security.mapper.AuthMapper;
import com.dino.security.model.User;
import com.dino.security.repository.UserRepository;
import com.dino.security.service.AuthenticationService;
import com.dino.security.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = repository.findByEmail(request.getEmail()).orElseThrow();
        return AuthMapper.toAuthenticationDTO(user, jwtService.generateToken(user));
    }

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        User user = repository.save(AuthMapper.toEntity(request, passwordEncoder.encode(request.getPassword())));
        return AuthMapper.toAuthenticationDTO(user, jwtService.generateToken(user));
    }
}
