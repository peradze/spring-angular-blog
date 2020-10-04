package com.tornikeperadze.blog.service.impl;

import com.tornikeperadze.blog.dto.request.CommentRequest;
import com.tornikeperadze.blog.model.Comment;
import com.tornikeperadze.blog.repository.CommentRepository;
import com.tornikeperadze.blog.service.CommentService;
import com.tornikeperadze.blog.service.PostService;
import com.tornikeperadze.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PostService postService;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, UserService userService, PostService postService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
    }

    @Override
    public List<Comment> getAllCommentByPostId(Long id) {
        return commentRepository.findByPost_Id(id);
    }

    @Override
    public void save(CommentRequest commentDto) {
        try {
            Comment comment = new Comment();
            comment.setComment(commentDto.getComment());
            comment.setCreatedAt(LocalDateTime.now());
            comment.setUser(userService.getByEmail(commentDto.getUserEmail()));
            comment.setPost(postService.getById(commentDto.getPostId()));
            commentRepository.save(comment);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
