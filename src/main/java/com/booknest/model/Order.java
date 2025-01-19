package com.booknest.model;

import java.util.Date;
import java.util.List;

public class Order {
    private String id;       // _id in Mongo
    private String userId;   // referencing user
    private Date orderDate;
    private List<OrderItem> orderItems;
    private String address;  // New field for shipping address
    private String paymentInfo;  // New field for payment information

    public Order() {}

    public Order(String id, String userId, Date orderDate, List<OrderItem> orderItems, String address, String paymentInfo) {
        this.id = id;
        this.userId = userId;
        this.orderDate = orderDate;
        this.orderItems = orderItems;
        this.address = address;
        this.paymentInfo = paymentInfo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    // Getter and Setter for address
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // Getter and Setter for paymentInfo
    public String getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(String paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    // Method to retrieve the total price of the order
    public double getTotalPrice() {
        double total = 0;
        for (OrderItem item : orderItems) {
            total += item.getTotalPrice();
        }
        return total;
    }
}
