package com.dino.security.dto;

import lombok.*;

@Getter
public class RegisterRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
}