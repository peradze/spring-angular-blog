package com.tornikeperadze.blog.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CommentRequest {
    @NotNull
    private Long postId;
    @NotBlank
    private String comment;
    @JsonIgnore
    private String userEmail;
}
