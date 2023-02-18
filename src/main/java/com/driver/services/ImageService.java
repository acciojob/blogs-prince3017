package com.driver.services;

import com.driver.models.*;
import com.driver.models.Image;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Blog blog = blogRepository2.findById(blogId).get();
        com.driver.models.Image image = new com.driver.models.Image(description, dimensions, blog);
        imageRepository2.save(image);
        blog.getImages().add(image);
        return image;
    }

    public void deleteImage(Integer id){
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        Image image = imageRepository2.findById(id).get();
        String[] imageDimen = image.getDimensions().split("X");
        String[] screenDimen = screenDimensions.split("X");
        int result = (Integer.parseInt(screenDimen[0]) / Integer.parseInt(imageDimen[0])) * (Integer.parseInt(screenDimen[1]) / Integer.parseInt(imageDimen[1]));
        return result;
    }
}
