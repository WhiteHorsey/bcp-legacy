package com.example.basebackend.service.impl;

import com.example.basebackend.exception.errors.AlreadyExistsException;
import com.example.basebackend.exception.errors.NotFoundException;
import com.example.basebackend.exception.errors.TokenRefreshException;
import com.example.basebackend.model.ERole;
import com.example.basebackend.model.RefreshToken;
import com.example.basebackend.model.Role;
import com.example.basebackend.model.User;
import com.example.basebackend.payload.request.LogOutRequest;
import com.example.basebackend.payload.request.LoginRequest;
import com.example.basebackend.payload.request.SignupRequest;
import com.example.basebackend.payload.request.TokenRefreshRequest;
import com.example.basebackend.payload.response.JwtResponse;
import com.example.basebackend.payload.response.TokenRefreshResponse;
import com.example.basebackend.repository.RoleRepository;
import com.example.basebackend.repository.UserRepository;
import com.example.basebackend.security.jwt.JwtUtils;
import com.example.basebackend.security.services.RefreshTokenService;
import com.example.basebackend.security.services.UserDetailsImpl;
import com.example.basebackend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private RefreshTokenService refreshTokenService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public JwtResponse signIn(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String jwt = jwtUtils.generateJwtToken(userDetails);

        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());
        return new JwtResponse(
                jwt,
                refreshToken.getToken(),
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);
    }

    @Override
    public String signUp(SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new AlreadyExistsException("Error: Username is already taken!");
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new AlreadyExistsException("Error: Email is already use!");
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new NotFoundException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new NotFoundException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new NotFoundException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new NotFoundException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);
        return "User registered successfully!";
    }

    @Override
    public TokenRefreshResponse refreshToken(TokenRefreshRequest refreshRequest) {
        String requestRefreshToken = refreshRequest.getRefreshToken();
        RefreshToken refreshToken = refreshTokenService.findByToken(requestRefreshToken)
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken, "Refresh token is not in database!"));
        if (refreshTokenService.isRefreshTokenExpired(refreshToken)) {
            throw new TokenRefreshException(refreshToken.getToken(), "Refresh token was expired. Please make a new signin request");
        } else {
            String token = jwtUtils.generateTokenFromUsername(refreshToken.getUser().getUsername());
            return new TokenRefreshResponse(token, requestRefreshToken);
        }
    }

    @Override
    public void logoutUser(Long userId) {
        refreshTokenService.deleteByUserId(userId);
    }
}
