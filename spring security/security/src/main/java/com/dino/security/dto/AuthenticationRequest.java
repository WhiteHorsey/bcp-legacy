package com.dino.security.dto;

import lombok.*;

@Getter
public class AuthenticationRequest {
    private String email;
    private String password;
}