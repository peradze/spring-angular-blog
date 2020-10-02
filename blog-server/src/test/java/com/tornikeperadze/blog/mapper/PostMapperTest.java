package com.tornikeperadze.blog.mapper;

import com.tornikeperadze.blog.model.Post;
import com.tornikeperadze.blog.payload.request.PostRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostMapperTest {
    PostRequest postRequest = new PostRequest();

    @BeforeEach
    void setUp() {
        postRequest.setTitle("title");
        postRequest.setContent("content");
        postRequest.setCategoryId(1);
        postRequest.setUserEmail("test@test.com");
    }

    @Test
    void postDtoToPost() {
        Post post = PostMapper.INSTANCE.postDtoToPost(postRequest);

        assertEquals(post.getTitle(), postRequest.getTitle());
        assertEquals(post.getContent(), postRequest.getContent());
        assertEquals(post.getCategory().getId(), postRequest.getCategoryId());
        assertEquals(post.getUser().getEmail(), postRequest.getUserEmail());
    }
}
