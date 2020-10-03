package com.tornikeperadze.blog.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ProfileDto {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private String email;
}
