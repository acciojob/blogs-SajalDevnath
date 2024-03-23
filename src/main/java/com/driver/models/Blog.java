package com.driver.models;

import java.util.ArrayList;
import java.util.List;

public class Blog {
    private int id;
    private String title;
    private String content;
    private User author;
    private List<String> images;

    public Blog() {
        this.images = new ArrayList<>();
    }

    public Blog(String title, String content, User author) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.images = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public void addImage(String image) {
        images.add(image);
    }

    public void deleteImage(String image) {
        images.remove(image);
    }
}
