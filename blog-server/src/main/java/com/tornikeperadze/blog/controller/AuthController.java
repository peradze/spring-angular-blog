package com.tornikeperadze.blog.controller;

import com.tornikeperadze.blog.model.Role;
import com.tornikeperadze.blog.model.User;
import com.tornikeperadze.blog.dto.request.LoginRequest;
import com.tornikeperadze.blog.dto.request.SignupRequest;
import com.tornikeperadze.blog.dto.response.JwtResponse;
import com.tornikeperadze.blog.dto.response.MessageResponse;
import com.tornikeperadze.blog.security.jwt.JwtUtils;
import com.tornikeperadze.blog.service.RoleService;
import com.tornikeperadze.blog.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;

@RestController
@Validated
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    public AuthController(AuthenticationManager authenticationManager, UserService userService,
                          RoleService roleService, PasswordEncoder encoder, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.roleService = roleService;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        if (!userService.existsByEmail(loginRequest.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email or password does not match");
        }

        User user = userService.getByEmail(loginRequest.getEmail());
        if (!encoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email or password does not match");
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        User authUser = (User) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt, authUser.getRoleNames()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userService.existsByEmail(signUpRequest.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is already in use!");
        }

        Optional<Role> userRole = roleService.getAll()
                .stream()
                .filter(role -> role.getName().equals(Role.Roles.ROLE_USER))
                .findFirst();

        User user = User.builder()
                .email(signUpRequest.getEmail())
                .password(encoder.encode(signUpRequest.getPassword()))
                .isAccExpired(false)
                .isAccLocked(false)
                .isCredExpired(false)
                .isEnabled(true)
                .roles(Set.of(userRole.get()))
                .build();

        userService.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
