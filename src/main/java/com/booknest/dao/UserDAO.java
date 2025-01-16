package com.booknest.dao;

import com.booknest.model.User;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.mongodb.client.model.Filters.*;

public class UserDAO {

    private static final Logger LOGGER = Logger.getLogger(UserDAO.class.getName());

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
                    .append("email", user.getEmail())
                    .append("role", "user"); // Default role is 'user'

            usersCollection.insertOne(doc);

            // Retrieve generated _id
            ObjectId id = doc.getObjectId("_id");
            user.setId(id.toHexString());

            return true;

        } catch (Exception e) {
            // Log the exception instead of printing stack trace
            LOGGER.log(Level.SEVERE, "Error registering user: " + user.getUsername(), e);
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
                user.setRole(found.getString("role")); // Fetch the user's role
                return user;
            }

        } catch (Exception e) {
            // Log the exception
            LOGGER.log(Level.SEVERE, "Error during authentication for username=" + username, e);
        }
        return null;
    }
}
