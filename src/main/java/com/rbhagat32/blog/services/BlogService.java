package com.rbhagat32.blog.services;

import com.rbhagat32.blog.models.BlogModel;
import com.rbhagat32.blog.repositories.BlogRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;

    public List<BlogModel> getAllBlogs(){
        return blogRepository.findAll();
    }

    public BlogModel createBlog(BlogModel blog) {
        blog.setCreatedAt(new Date());
        return blogRepository.save(blog);
    }

    public Optional<BlogModel> getBlogById(ObjectId blogId) {
        return blogRepository.findById(blogId);
    }

    public BlogModel updateBlogById(ObjectId blogId, BlogModel newBlog) {
        BlogModel blog = getBlogById(blogId).orElse(null);

        if (blog != null){
            blog.setTitle(newBlog.getTitle()!= null && !newBlog.getTitle().isEmpty() ? newBlog.getTitle() : blog.getTitle());
            blog.setContent(newBlog.getContent()!= null && !newBlog.getTitle().isEmpty() ? newBlog.getContent() : blog.getContent());
            blogRepository.save(blog);
        }

        return blog;
    }

    public Optional<BlogModel> deleteBlogById(ObjectId blogId) {
        Optional<BlogModel> blog = blogRepository.findById(blogId);
        blog.ifPresent(b -> blogRepository.deleteById(blogId));
        return blog;
    }
}