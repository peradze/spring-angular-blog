package com.tornikeperadze.blog.service;

import com.tornikeperadze.blog.model.Category;
import com.tornikeperadze.blog.payload.request.CategoryRequest;

public interface CategoryService {
    Category save(CategoryRequest category);
}
