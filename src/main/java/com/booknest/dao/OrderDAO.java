package com.booknest.dao;

import com.booknest.model.CartItem;
import com.booknest.model.Order;
import com.booknest.model.OrderItem;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.*;
import static com.mongodb.client.model.Filters.eq;

public class OrderDAO {

    // Create a new order
    public String createOrder(String userId, List<CartItem> cartItems) {
        try {
            MongoDatabase db = DBConnection.getDatabase();
            MongoCollection<Document> ordersCollection = db.getCollection("orders");

            // Build orderItems array
            List<Document> orderItemsDocs = new ArrayList<>();
            for (CartItem cartItem : cartItems) {
                Document itemDoc = new Document("bookId", cartItem.getBook().getId())
                        .append("quantity", cartItem.getQuantity())
                        .append("price", cartItem.getBook().getPrice());
                orderItemsDocs.add(itemDoc);
            }

            Document orderDoc = new Document("userId", userId)
                    .append("orderDate", new Date())
                    .append("orderItems", orderItemsDocs);

            ordersCollection.insertOne(orderDoc);
            ObjectId insertedId = orderDoc.getObjectId("_id");
            return insertedId.toHexString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Retrieve all orders by user (order history)
    public List<Order> getOrdersByUser(String userId) {
        List<Order> orders = new ArrayList<>();
        MongoDatabase db = DBConnection.getDatabase();
        MongoCollection<Document> ordersCollection = db.getCollection("orders");

        for (Document doc : ordersCollection.find(eq("userId", userId))) {
            Order order = new Order();
            order.setId(doc.getObjectId("_id").toHexString());
            order.setUserId(doc.getString("userId"));
            order.setOrderDate(doc.getDate("orderDate"));

            List<Document> itemsDoc = (List<Document>) doc.get("orderItems");
            List<OrderItem> orderItems = new ArrayList<>();
            if (itemsDoc != null) {
                for (Document item : itemsDoc) {
                    OrderItem oi = new OrderItem();
                    oi.setBookId(item.getString("bookId"));
                    oi.setQuantity(item.getInteger("quantity"));
                    oi.setPrice(item.getDouble("price"));
                    orderItems.add(oi);
                }
            }
            order.setOrderItems(orderItems);

            orders.add(order);
        }
        return orders;
    }
}
