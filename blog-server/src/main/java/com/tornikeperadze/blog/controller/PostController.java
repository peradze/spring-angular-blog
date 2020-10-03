package com.tornikeperadze.blog.controller;

import com.tornikeperadze.blog.dto.request.PostRequest;
import com.tornikeperadze.blog.dto.response.MessageResponse;
import com.tornikeperadze.blog.dto.response.PostDetailResponse;
import com.tornikeperadze.blog.dto.response.PostListResponse;
import com.tornikeperadze.blog.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<PostListResponse>> getAll() {
        return ResponseEntity.ok(postService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDetailResponse> getPost(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPostDetail(id));
    }
}
