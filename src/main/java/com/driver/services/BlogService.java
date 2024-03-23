package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BlogService {

    private final BlogRepository blogRepository;
    private final UserRepository userRepository;

    @Autowired
    public BlogService(BlogRepository blogRepository, UserRepository userRepository) {
        this.blogRepository = blogRepository;
        this.userRepository = userRepository;
    }

    public Blog createAndReturnBlog(Integer userId, String title, String content) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return null; 
        }
        
        Blog newBlog = new Blog(title, content, user);
        newBlog.setCreatedAt(new Date()); 

        return blogRepository.save(newBlog);
    }

    public void deleteBlog(int blogId) {
        blogRepository.deleteById(blogId);
    }
}
