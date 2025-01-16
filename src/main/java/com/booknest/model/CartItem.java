package com.booknest.model;

public class CartItem {
    private Book book;
    private int quantity;

    public CartItem() {}

    public CartItem(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
    }

    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getTotalPrice() {
        return book.getPrice() * quantity;
    }
}
