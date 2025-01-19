package com.booknest.model;

public class Book {
    private String id;      // MongoDB ObjectId as String
    private String title;
    private String author;
    private String category; // Field for category
    private double price;
    private int stock;
    private String image;   // New field to store the image path or URL

    // Default constructor
    public Book() {}

    // Parameterized constructor for creating a new Book object
    public Book(String id, String title, String author, String category, double price, int stock, String image) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.image = image;  // Initialize the image field
    }

    // Getters and Setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image; // Setter for image field
    }

    // You can add additional methods for validation, conversion, etc., if necessary
}
