package com.rbhagat32.blog.controllers;

import com.rbhagat32.blog.models.BlogModel;
import com.rbhagat32.blog.services.BlogService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @GetMapping
    public List<BlogModel> getAllBlogs() {
        return blogService.getAllBlogs();
    }

    @PostMapping
    public BlogModel createBlog(@RequestBody BlogModel body) {
        blogService.createBlog(body);
        return body;
    }

    @GetMapping("/{blogId}")
    public BlogModel getBlogById(@PathVariable ObjectId blogId) {
        return blogService.getBlogById(blogId).orElse(null);
    }

    @PutMapping("/{blogId}")
    public BlogModel updateBlogById(@PathVariable ObjectId blogId, @RequestBody BlogModel body) {
        return blogService.updateBlogById(blogId, body);
    }

    @DeleteMapping("/{blogId}")
    public BlogModel deleteBlogById(@PathVariable ObjectId blogId) {
        return blogService.deleteBlogById(blogId).orElse(null);
    }
}