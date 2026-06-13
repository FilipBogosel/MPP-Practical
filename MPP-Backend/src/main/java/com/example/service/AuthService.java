package com.example.service;

import com.example.dto.AuthResponse;
import com.example.dto.LoginRequest;
import com.example.dto.RegisterRequest;
import com.example.entity.AppUser;
import com.example.entity.Role;
import com.example.repository.UserRepository;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import java.time.Duration;
import java.time.Instant;
import java.util.Set;

@ApplicationScoped
public class AuthService {

    @Inject
    UserRepository userRepository;

    public AuthResponse login(LoginRequest req) {
        AppUser user = userRepository.findByUsername(req.username())
                .orElseThrow(() -> new WebApplicationException("Invalid username or password", Response.Status.UNAUTHORIZED));

        if (!BcryptUtil.matches(req.password(), user.getPasswordHash())) {
            throw new WebApplicationException("Invalid username or password", Response.Status.UNAUTHORIZED);
        }

        return new AuthResponse(buildToken(user), user.getUsername(), user.getEmail(), user.getRole().name());
    }

    @Transactional
    public AuthResponse register(RegisterRequest req) {
        if (userRepository.existsByUsername(req.username())) {
            throw new WebApplicationException("Username already taken", Response.Status.CONFLICT);
        }
        if (userRepository.existsByEmail(req.email())) {
            throw new WebApplicationException("Email already registered", Response.Status.CONFLICT);
        }

        Role role;
        try {
            role = Role.valueOf(req.role().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new WebApplicationException("Invalid role: " + req.role(), Response.Status.BAD_REQUEST);
        }

        AppUser user = new AppUser();
        user.setUsername(req.username());
        user.setEmail(req.email());
        user.setPasswordHash(BcryptUtil.bcryptHash(req.password()));
        user.setRole(role);
        user.setCreatedAt(Instant.now());

        userRepository.persist(user);

        return new AuthResponse(buildToken(user), user.getUsername(), user.getEmail(), user.getRole().name());
    }

    private String buildToken(AppUser user) {
        return Jwt.issuer("https://mpp-practical.com")
                .upn(user.getUsername())
                .groups(Set.of(user.getRole().name()))
                .expiresIn(Duration.ofHours(24))
                .sign();
    }
}
