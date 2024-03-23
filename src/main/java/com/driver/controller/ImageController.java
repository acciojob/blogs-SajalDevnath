package com.driver.controller;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.driver.services.BlogService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/images")
public class ImageController {

    private final ImageService imageService;
    private final BlogService blogService;

    @Autowired
    public ImageController(ImageService imageService, BlogService blogService) {
        this.imageService = imageService;
        this.blogService = blogService;
    }

    @PostMapping("/{blogId}/add-image")
    public ResponseEntity<String> addImage(@PathVariable int blogId, @RequestParam String description, @RequestParam String dimensions) {
        // Assuming you have a way to retrieve the Blog object by blogId
        Blog blog = blogService.findBlogById(blogId);
        if (blog == null) {
            return new ResponseEntity<>("Blog not found", HttpStatus.NOT_FOUND);
        }
        
        Image newImage = new Image(description, dimensions);
        blogService.addImageToBlog(blogId, newImage);
        return new ResponseEntity<>("Added image successfully", HttpStatus.OK);
    }

    @GetMapping("/countImagesInScreen/{id}/{screenDimensions}")
    public ResponseEntity<Integer> countImagesInScreen(@PathVariable int id, @PathVariable String screenDimensions) {
        // Logic to count number of images that can fit into given screen size
        int count = imageService.countImagesFitScreen(id, screenDimensions);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable int id) {
        imageService.deleteImageById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
