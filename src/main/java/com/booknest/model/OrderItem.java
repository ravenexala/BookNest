package com.booknest.model;

public class OrderItem {
    private String bookId;     // referencing Book's _id
    private int quantity;
    private double price;
    private Book book;  // Add the Book field to store the Book object

    public OrderItem() {}

    public OrderItem(String bookId, int quantity, double price) {
        this.bookId = bookId;
        this.quantity = quantity;
        this.price = price;
    }

    public String getBookId() { return bookId; }
    public void setBookId(String bookId) { this.bookId = bookId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public Book getBook() { return book; }  // Add getter for book
    public void setBook(Book book) { this.book = book; }  // Add setter for book

    // Add method to calculate total price
    public double getTotalPrice() {
        return this.quantity * this.price;
    }
}
