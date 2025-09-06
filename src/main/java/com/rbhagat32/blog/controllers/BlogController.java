package com.rbhagat32.blog.controllers;

import com.rbhagat32.blog.models.BlogModel;
import com.rbhagat32.blog.services.BlogService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @GetMapping
    public ResponseEntity<List<BlogModel>> getAllBlogs() {
        List<BlogModel> allBlogs = blogService.getAllBlogs();

        if (allBlogs == null || allBlogs.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(allBlogs, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BlogModel> createBlog(@RequestBody BlogModel body) {
        try {
            BlogModel newBlog = blogService.createBlog(body);
            return new ResponseEntity<>(newBlog, HttpStatus.CREATED);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{blogId}")
    public ResponseEntity<BlogModel> getBlogById(@PathVariable ObjectId blogId) {
        Optional<BlogModel> blog = blogService.getBlogById(blogId);

        if (blog.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(blog.get(),  HttpStatus.OK);
    }

    @PutMapping("/{blogId}")
    public ResponseEntity<BlogModel> updateBlogById(@PathVariable ObjectId blogId, @RequestBody BlogModel body) {
        try {
            BlogModel updatedBlog = blogService.updateBlogById(blogId, body);
            return new ResponseEntity<>(updatedBlog, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{blogId}")
    public ResponseEntity<BlogModel> deleteBlogById(@PathVariable ObjectId blogId) {
        Optional<BlogModel> deletedBlog = blogService.deleteBlogById(blogId);

        if (deletedBlog.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(deletedBlog.get(), HttpStatus.OK);
    }
}