package com.tornikeperadze.blog.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostNumberInCategory {
    private String name;
    private Long count;
}
