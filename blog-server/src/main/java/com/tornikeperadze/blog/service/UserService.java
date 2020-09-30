package com.tornikeperadze.blog.service;

import com.tornikeperadze.blog.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    boolean existsByEmail(String email);
    User getByEmail(String email);
    User save(User user);
}
