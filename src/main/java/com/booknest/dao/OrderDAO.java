package com.booknest.dao;

import com.booknest.model.CartItem;
import com.booknest.model.Order;
import com.booknest.model.OrderItem;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.mongodb.client.model.Filters.eq;

public class OrderDAO {

    private static final Logger LOGGER = Logger.getLogger(OrderDAO.class.getName());

    // Create a new order
    public String createOrder(String userId, List<CartItem> cartItems) {
        try {
            MongoDatabase db = DBConnection.getDatabase();
            MongoCollection<Document> ordersCollection = db.getCollection("orders");

            // Build orderItems array
            List<Document> orderItemsDocs = new ArrayList<>();
            for (CartItem cartItem : cartItems) {
                orderItemsDocs.add(new Document("bookId", cartItem.getBook().getId())
                        .append("quantity", cartItem.getQuantity())
                        .append("price", cartItem.getBook().getPrice()));
            }

            Document orderDoc = new Document("userId", userId)
                    .append("orderDate", new Date())
                    .append("orderItems", orderItemsDocs);

            ordersCollection.insertOne(orderDoc);
            ObjectId insertedId = orderDoc.getObjectId("_id");
            return insertedId.toHexString();

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error creating order for userId=" + userId, e);
            return null;
        }
    }

    // Retrieve all orders by user (order history)
    public List<Order> getOrdersByUser(String userId) {
        List<Order> orders = new ArrayList<>();

        try {
            MongoDatabase db = DBConnection.getDatabase();
            MongoCollection<Document> ordersCollection = db.getCollection("orders");

            for (Document doc : ordersCollection.find(eq("userId", userId))) {
                Order order = new Order();
                order.setId(doc.getObjectId("_id").toHexString());
                order.setUserId(doc.getString("userId"));
                order.setOrderDate(doc.getDate("orderDate"));

                // Extract and set orderItems using a helper method
                order.setOrderItems(extractOrderItems(doc.get("orderItems")));

                orders.add(order);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error retrieving orders by userId=" + userId, e);
        }

        return orders;
    }

    // Helper method to extract order items from raw data
    private List<OrderItem> extractOrderItems(Object rawItems) {
        List<OrderItem> orderItems = new ArrayList<>();

        if (rawItems instanceof List<?> itemsList) { // Modern pattern matching with `instanceof`
            for (Object itemObj : itemsList) {
                if (itemObj instanceof Document itemDoc) { // Nested pattern matching
                    OrderItem oi = new OrderItem();
                    oi.setBookId(itemDoc.getString("bookId"));
                    oi.setQuantity(itemDoc.getInteger("quantity"));
                    oi.setPrice(itemDoc.getDouble("price"));
                    orderItems.add(oi);
                }
            }
        }

        return orderItems;
    }
}
