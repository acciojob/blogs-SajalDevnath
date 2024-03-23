package com.driver.services;

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
        // Find the blog by its ID
        Blog blog = blogRepository.findById(blogId).orElse(null);
        if (blog == null) {
            return null; // Handle blog not found error
        }

        // Create a new Image instance
        Image newImage = new Image(description, dimensions);

        // Add the image to the blog
        blog.addImage(newImage);
        blogRepository.save(blog); // Save the blog to update changes

        // Save the image
        return imageRepository.save(newImage);
    }

    public void deleteImage(Integer id) {
        // Delete the image by its ID
        imageRepository.deleteById(id);
    }

    public int countImagesInScreen(Integer blogId, String screenDimensions) {
    // Find the blog by its ID
    Blog blog = blogRepository.findById(blogId).orElse(null);
    if (blog == null) {
        return 0; // Return 0 if blog not found
    }

    // Get the width of the screen from the screenDimensions string
    int screenWidth = extractScreenWidth(screenDimensions);
    if (screenWidth <= 0) {
        return 0; // Return 0 if screen width is invalid
    }

    // Get all images associated with the blog
    List<Image> images = blog.getImages();

    // Count the number of images that can fit into the screen width
    int count = 0;
    for (Image image : images) {
        int imageWidth = extractImageWidth(image.getDimensions());
        if (imageWidth > 0 && imageWidth <= screenWidth) {
            count++;
        }
    }

    return count;
}

// Method to extract image width from imageDimensions
private int extractImageWidth(String imageDimensions) {
    // Split the image dimensions string by 'x'
    String[] parts = imageDimensions.split("x");
    if (parts.length != 2) {
        return 0; // Invalid dimensions format
    }

    try {
        // Parse and return the width (first part)
        return Integer.parseInt(parts[0]);
    } catch (NumberFormatException e) {
        return 0; // Unable to parse width
    }
}

// Method to extract screen width from screenDimensions
private int extractScreenWidth(String screenDimensions) {
    // Split the screen dimensions string by 'x'
    String[] parts = screenDimensions.split("x");
    if (parts.length != 2) {
        return 0; // Invalid dimensions format
    }

    try {
        // Parse and return the width (first part)
        return Integer.parseInt(parts[0]);
    } catch (NumberFormatException e) {
        return 0; // Unable to parse width
    }
}

}
