package com.dino.security.mapper;

import com.dino.security.dto.AuthenticationResponse;
import com.dino.security.dto.RegisterRequest;
import com.dino.security.model.User;

import static com.dino.security.utils.Role.USER;

public class AuthMapper {

    public static User toEntity(RegisterRequest request, String password) {
        return User.builder()
                .firstName(request.getFirstname())
                .lastName(request.getLastname())
                .email(request.getEmail())
                .password(password)
                .role(USER)
                .build();
    }

    public static AuthenticationResponse toAuthenticationDTO(User user, String token) {
        return AuthenticationResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .token(token)
                .build();
    }
}
