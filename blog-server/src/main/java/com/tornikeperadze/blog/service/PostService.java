package com.tornikeperadze.blog.service;

import com.tornikeperadze.blog.payload.request.PostRequest;

public interface PostService {
    PostRequest save(PostRequest post);
}
