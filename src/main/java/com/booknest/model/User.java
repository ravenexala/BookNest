package com.booknest.model;

public class User {
    private String id;       // MongoDB _id stored as a String
    private String username;
    private String password;
    private String email;
    private String role;     // Added role (e.g., "admin", "user")

    // Default constructor
    public User() {}

    // Parameterized constructor to initialize a user object
    public User(String id, String username, String password, String email, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // The getRole() method will be used for checking if the logged-in user is an admin
    public String getRole() {
        return role;
    }

    // Set the user's role (admin, user, etc.)
    public void setRole(String role) {
        this.role = role;
    }

    // Optional: You can add a method to check if the user is an admin
    public boolean isAdmin() {
        return "admin".equalsIgnoreCase(this.role);
    }
}
