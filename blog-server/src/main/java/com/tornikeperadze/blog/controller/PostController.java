package com.tornikeperadze.blog.controller;

import com.tornikeperadze.blog.payload.request.PostRequest;
import com.tornikeperadze.blog.payload.response.MessageResponse;
import com.tornikeperadze.blog.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/posts")
@Validated
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<?> addPost(@Valid @RequestBody PostRequest postRequest) {
        postRequest.setUserEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        postService.save(postRequest);
        return ResponseEntity.ok(new MessageResponse("Post created successfully"));
    }
}
