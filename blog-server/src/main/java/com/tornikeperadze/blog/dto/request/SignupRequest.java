package com.tornikeperadze.blog.dto.request;


import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SignupRequest {
    @NotBlank
    @Email
    @Size(max = 32)
    private String email;
    @NotBlank
    @Size(min = 5, max = 32)
    private String password;
}
