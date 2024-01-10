package com.example.basebackend.service.impl;



import com.example.basebackend.dto.request.LoginRequest;
import com.example.basebackend.dto.request.SignupRequest;
import com.example.basebackend.dto.response.JwtResponse;
import com.example.basebackend.dto.response.TokenRefreshResponse;
import com.example.basebackend.exception.errors.AlreadyExistsException;
import com.example.basebackend.exception.errors.NotFoundException;
import com.example.basebackend.exception.errors.TokenRefreshException;
import com.example.basebackend.model.ERole;
import com.example.basebackend.model.RefreshToken;
import com.example.basebackend.model.Role;
import com.example.basebackend.model.User;
import com.example.basebackend.repository.RoleRepository;
import com.example.basebackend.repository.UserRepository;
import com.example.basebackend.security.jwt.JwtUtils;
import com.example.basebackend.security.services.RefreshTokenService;
import com.example.basebackend.security.services.UserDetailsImpl;
import com.example.basebackend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

import static com.example.basebackend.model.ERole.ADMIN;
import static com.example.basebackend.model.ERole.USER;


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
    public JwtResponse signIn(LoginRequest loginRequest, HttpServletResponse response) {
        // SECURITY
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        // CREATE ACCESS TOKEN
        String accessTokenJWT = jwtUtils.generateJwtToken(userDetails);

        // CREATE REFRESH TOKEN
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

        // CREATE REFRESH TOKEN COOKIE
        Cookie jwtRefreshCookie = jwtUtils.generateRefreshJwtCookie(refreshToken.getToken());
        response.addCookie(jwtRefreshCookie);

        return JwtResponse.builder().accessToken(accessTokenJWT).tokenType("Bearer").build();

    }

    @Override
    public String signUp(SignupRequest signUpRequest) {
        if (Boolean.TRUE.equals(userRepository.existsByEmail(signUpRequest.getEmail()))) {
            throw new AlreadyExistsException("Error: Email is already use!");
        }

        // Create new user's account
        User user = new User(signUpRequest.getEmail(),
                signUpRequest.getFirstName(),
                signUpRequest.getLastName(),
                signUpRequest.getPhoneNumber(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = getRole(USER);
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = getRole(ADMIN);
                        roles.add(adminRole);
                        break;
                    default:
                        Role userRole = getRole(USER);
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        return "User registered successfully!";
    }

    private Role getRole(ERole role) {
        return roleRepository.findByName(role).orElseThrow(() -> new NotFoundException("Error: Role is not found."));
    }

    @Override
    public ResponseEntity<?> refreshToken(String jwtRefresh) {
        if ((jwtRefresh != null) && (jwtRefresh.length() > 0)) {
            return refreshTokenService.findByToken(jwtRefresh)
                    .map(refreshTokenService::verifyExpiration)
                    .map(RefreshToken::getUser)
                    .map(user -> {
                        String newAccessToken = jwtUtils.generateJwtToken(UserDetailsImpl.build(user));
                        return ResponseEntity.ok().body(new TokenRefreshResponse(newAccessToken));
                    })
                    .orElseThrow(() -> new TokenRefreshException(jwtRefresh,
                            "Refresh token is not in database!"));
        }
        return ResponseEntity.badRequest().body("Refresh Token is empty!");

    }

    @Override
    public String logoutUser(Long userId, HttpServletResponse response) {
        refreshTokenService.deleteByUserId(userId);
        Cookie jwtRefreshCookie = jwtUtils.generateNullRefreshJwtCookie();
        response.addCookie(jwtRefreshCookie);
        return "You've been signed out!";
    }
}
