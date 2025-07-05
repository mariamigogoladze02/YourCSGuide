package com.task.yourcsguide.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BlogPostRequestDTO {
    private String title;
    private String content;
    private List<String> files;

}

