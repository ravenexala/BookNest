package com.booknest.model;

public class OrderItem {
    private String bookId;     // referencing Book's _id
    private int quantity;
    private double price;

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
}
