package com.tornikeperadze.blog.controller;

import com.tornikeperadze.blog.model.User;
import com.tornikeperadze.blog.payload.request.LoginRequest;
import com.tornikeperadze.blog.payload.response.JwtResponse;
import com.tornikeperadze.blog.security.jwt.JwtUtils;
import com.tornikeperadze.blog.service.RoleService;
import com.tornikeperadze.blog.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private UserService userService;
    private RoleService roleService;
    private PasswordEncoder encoder;
    private JwtUtils jwtUtils;

    public AuthController(AuthenticationManager authenticationManager, UserService userService, RoleService roleService, PasswordEncoder encoder, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.roleService = roleService;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticationUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        User authUser = (User) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt, authUser.getRoleNames()));
    }
}
