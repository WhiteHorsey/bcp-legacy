package com.dino.security.service;

import com.dino.security.dto.AuthenticationRequest;
import com.dino.security.dto.AuthenticationResponse;
import com.dino.security.dto.RegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request);

    AuthenticationResponse register(RegisterRequest request);
}
