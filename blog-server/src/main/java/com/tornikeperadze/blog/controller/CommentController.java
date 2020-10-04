package com.tornikeperadze.blog.controller;

import com.tornikeperadze.blog.dto.request.CommentRequest;
import com.tornikeperadze.blog.dto.response.MessageResponse;
import com.tornikeperadze.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/comments")
@Validated
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<?> addComment(@Valid @RequestBody CommentRequest comment) {
        comment.setUserEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        commentService.save(comment);
        return ResponseEntity.ok(new MessageResponse("Comment created"));
    }
}
