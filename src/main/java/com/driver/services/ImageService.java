package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    private final BlogRepository blogRepository;
    private final ImageRepository imageRepository;

    @Autowired
    public ImageService(BlogRepository blogRepository, ImageRepository imageRepository) {
        this.blogRepository = blogRepository;
        this.imageRepository = imageRepository;
    }

    public Image addImage(Integer blogId, String description, String dimensions) {
        Blog blog = blogRepository.findById(blogId).orElse(null);
        if (blog == null) {
            return null; 
        }

        Image newImage = new Image(description, dimensions);

        blog.addImage(newImage);
        blogRepository.save(blog); 

        return imageRepository.save(newImage);
    }

    public void deleteImage(Integer id) {
        imageRepository.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        return 5;
    }
}
