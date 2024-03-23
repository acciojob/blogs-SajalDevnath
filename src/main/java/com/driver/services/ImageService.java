import com.driver.models.Image;
import com.driver.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public int countImagesInScreen(String screenDimensions) {
        // Fetch all images from the repository
        List<Image> images = imageRepository.findAll();

        // Calculate the number of images that can fit into the screen based on their dimensions
        int screenWidth = parseScreenWidth(screenDimensions); // Assuming screen width is extracted from the provided screenDimensions
        int totalImages = 0;
        for (Image image : images) {
            int imageWidth = parseImageWidth(image.getDimensions()); // Assuming image width is extracted from the image dimensions
            if (imageWidth <= screenWidth) {
                totalImages++;
            }
        }
        return totalImages;
    }

    private int parseScreenWidth(String screenDimensions) {
        // Assuming screen width is extracted from the provided screenDimensions
        String[] parts = screenDimensions.split("x");
        return Integer.parseInt(parts[0]);
    }

    private int parseImageWidth(String imageDimensions) {
        // Assuming image width is extracted from the image dimensions
        String[] parts = imageDimensions.split("x");
        return Integer.parseInt(parts[0]);
    }
}
