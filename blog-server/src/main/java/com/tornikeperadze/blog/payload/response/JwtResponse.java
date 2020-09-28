package com.tornikeperadze.blog.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class JwtResponse {
    private String jwt;
    private List<String> roles;
}
