package com.tornikeperadze.blog.mapper;

import com.tornikeperadze.blog.model.Post;
import com.tornikeperadze.blog.dto.request.PostRequest;
import com.tornikeperadze.blog.dto.response.PostListResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(source = "categoryId", target = "category.id")
    @Mapping(source = "userEmail", target = "user.email")
    Post postDtoToPost(PostRequest postRequest);

    List<PostListResponse> postsToPostListDto(List<Post> posts);
}
