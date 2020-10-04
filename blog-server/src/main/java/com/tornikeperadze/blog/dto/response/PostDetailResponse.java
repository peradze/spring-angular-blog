package com.tornikeperadze.blog.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tornikeperadze.blog.dto.CommentDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PostDetailResponse {
    private Long id;
    private String title;
    private String category;
    private String author;
    private String content;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime createdAt;
    private List<CommentDto> comments;
}
