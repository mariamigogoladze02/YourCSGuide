package com.task.yourcsguide.controller;


import com.task.yourcsguide.service.BlogPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/blogs")
public class BlogPostController {

    private final BlogPostService blogPostService;


}

