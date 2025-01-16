package com.booknest.model;

import java.util.Date;
import java.util.List;

public class Order {
    private String id;       // _id in Mongo
    private String userId;   // referencing user
    private Date orderDate;
    private List<OrderItem> orderItems;

    public Order() {}

    public Order(String id, String userId, Date orderDate, List<OrderItem> orderItems) {
        this.id = id;
        this.userId = userId;
        this.orderDate = orderDate;
        this.orderItems = orderItems;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public Date getOrderDate() { return orderDate; }
    public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }

    public List<OrderItem> getOrderItems() { return orderItems; }
    public void setOrderItems(List<OrderItem> orderItems) { this.orderItems = orderItems; }
}
