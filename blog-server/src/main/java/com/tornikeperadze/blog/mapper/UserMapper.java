package com.tornikeperadze.blog.mapper;

import com.tornikeperadze.blog.model.User;
import com.tornikeperadze.blog.dto.ProfileDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    ProfileDto userToProfileDto(User user);
}
