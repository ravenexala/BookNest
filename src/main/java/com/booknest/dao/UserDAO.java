package com.booknest.dao;

import com.booknest.model.User;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.*;

public class UserDAO {

    // Register new user
    public boolean registerUser(User user) {
        try {
            MongoDatabase db = DBConnection.getDatabase();
            MongoCollection<Document> usersCollection = db.getCollection("users");

            // Check if username already exists
            long count = usersCollection.countDocuments(eq("username", user.getUsername()));
            if (count > 0) {
                return false;  // user already exists
            }

            Document doc = new Document("username", user.getUsername())
                    .append("password", user.getPassword())
                    .append("email", user.getEmail());

            usersCollection.insertOne(doc);

            // Retrieve generated _id
            ObjectId id = doc.getObjectId("_id");
            user.setId(id.toHexString());

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Authenticate user (login)
    public User authenticate(String username, String password) {
        try {
            MongoDatabase db = DBConnection.getDatabase();
            MongoCollection<Document> usersCollection = db.getCollection("users");

            Document found = usersCollection.find(and(
                    eq("username", username),
                    eq("password", password)
            )).first();

            if (found != null) {
                User user = new User();
                user.setId(found.getObjectId("_id").toHexString());
                user.setUsername(found.getString("username"));
                user.setPassword(found.getString("password"));
                user.setEmail(found.getString("email"));
                return user;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
