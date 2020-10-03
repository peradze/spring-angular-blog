package com.tornikeperadze.blog.service;

import com.tornikeperadze.blog.payload.request.PostRequest;
import com.tornikeperadze.blog.payload.response.PostListResponse;

import java.util.List;

public interface PostService {
    PostRequest save(PostRequest post);
    List<PostListResponse> getAll();
}
