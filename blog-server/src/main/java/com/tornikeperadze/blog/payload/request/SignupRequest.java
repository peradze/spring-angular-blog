package com.tornikeperadze.blog.payload.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Setter
@Getter
@AllArgsConstructor
public class SignupRequest {
    @NotBlank
    @Email
    @Size(max = 32)
    private String email;
    @NotBlank
    @Size(min = 5, max = 32)
    private String password;
}
