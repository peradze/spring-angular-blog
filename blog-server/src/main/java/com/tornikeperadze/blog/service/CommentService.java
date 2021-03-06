package com.tornikeperadze.blog.service;

import com.tornikeperadze.blog.dto.request.CommentRequest;
import com.tornikeperadze.blog.model.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getAllCommentByPostId(Long id);
    void save(CommentRequest comment);
}
