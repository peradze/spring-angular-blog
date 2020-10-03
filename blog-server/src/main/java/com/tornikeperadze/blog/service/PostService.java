package com.tornikeperadze.blog.service;

import com.tornikeperadze.blog.dto.request.PostRequest;
import com.tornikeperadze.blog.dto.response.PostDetailResponse;
import com.tornikeperadze.blog.dto.response.PostListResponse;

import java.util.List;

public interface PostService {
    PostRequest save(PostRequest post);
    List<PostListResponse> getAll();
    PostDetailResponse getPostDetail(Long id);
}
