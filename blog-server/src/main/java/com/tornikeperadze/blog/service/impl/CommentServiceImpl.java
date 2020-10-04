package com.tornikeperadze.blog.service.impl;

import com.tornikeperadze.blog.model.Comment;
import com.tornikeperadze.blog.repository.CommentRepository;
import com.tornikeperadze.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> getAllCommentByPostId(Long id) {
        return commentRepository.findByPost_Id(id);
    }
}
