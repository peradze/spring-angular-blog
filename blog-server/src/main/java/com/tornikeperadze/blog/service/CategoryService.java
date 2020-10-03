package com.tornikeperadze.blog.service;

import com.tornikeperadze.blog.model.Category;
import com.tornikeperadze.blog.dto.request.CategoryRequest;
import com.tornikeperadze.blog.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    Category save(CategoryRequest category);
    List<CategoryResponse> getAll();
}
