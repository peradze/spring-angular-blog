package com.tornikeperadze.blog.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PostRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotNull
    private Integer categoryId;
    @JsonIgnore
    private String userEmail;
}
