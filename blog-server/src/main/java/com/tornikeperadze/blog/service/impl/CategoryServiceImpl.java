package com.tornikeperadze.blog.service.impl;

import com.tornikeperadze.blog.mapper.CategoryMapper;
import com.tornikeperadze.blog.model.Category;
import com.tornikeperadze.blog.dto.request.CategoryRequest;
import com.tornikeperadze.blog.dto.response.CategoryResponse;
import com.tornikeperadze.blog.repository.CategoryRepository;
import com.tornikeperadze.blog.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category save(CategoryRequest category) {
        return categoryRepository.save(CategoryMapper.INSTANCE.categoryDtoToCategory(category));
    }

    @Override
    public List<CategoryResponse> getAll() {
        return CategoryMapper.INSTANCE.categoriesToCategoryDto(categoryRepository.findAll());
    }
}
