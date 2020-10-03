package com.tornikeperadze.blog.service.impl;

import com.tornikeperadze.blog.mapper.PostMapper;
import com.tornikeperadze.blog.model.Post;
import com.tornikeperadze.blog.model.User;
import com.tornikeperadze.blog.dto.request.PostRequest;
import com.tornikeperadze.blog.dto.response.PostListResponse;
import com.tornikeperadze.blog.repository.PostRepository;
import com.tornikeperadze.blog.repository.UserRepository;
import com.tornikeperadze.blog.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostServiceImp implements PostService {
    private static final Logger logger = LoggerFactory.getLogger(PostServiceImp.class);
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostServiceImp(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public PostRequest save(PostRequest post) {
        try {
            Post p = PostMapper.INSTANCE.postDtoToPost(post);
            User user = userRepository.findByEmail(post.getUserEmail()).get();
            p.setUser(user);
            p.setCreatedAt(LocalDateTime.now());
            postRepository.save(p);
        } catch (Exception e) {
            logger.error("Cannot save to database. {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return post;
    }

    @Override
    public List<PostListResponse> getAll() {
        return PostMapper.INSTANCE.postsToPostListDto(postRepository.findAll());
    }
}
