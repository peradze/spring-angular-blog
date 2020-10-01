package com.tornikeperadze.blog.mapper;

import com.tornikeperadze.blog.model.Category;
import com.tornikeperadze.blog.payload.request.CategoryRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category categoryDtoToCategory(CategoryRequest category);
}
