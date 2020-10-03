package com.tornikeperadze.blog.service;

import com.tornikeperadze.blog.dto.request.PostRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PostServiceTest {
    private final PostService postService;
    PostRequest postRequest = new PostRequest();

    @Autowired
    PostServiceTest(PostService postService) {
        this.postService = postService;
    }

    @BeforeEach
    void setUp() {
        postRequest.setTitle("title");
        postRequest.setContent("content");
        postRequest.setCategoryId(1);
        postRequest.setUserEmail("admin@test.com");
    }

    @Test
    void save() {
        PostRequest save = postService.save(postRequest);

        assertEquals(save.getCategoryId(), postRequest.getCategoryId());
        assertEquals(save.getUserEmail(), postRequest.getUserEmail());
    }
}
