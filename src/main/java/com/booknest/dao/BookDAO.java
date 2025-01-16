package com.booknest.dao;

import com.booknest.model.Book;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

public class BookDAO {

    // Retrieve all books
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        MongoDatabase db = DBConnection.getDatabase();
        MongoCollection<Document> booksCollection = db.getCollection("books");

        for (Document doc : booksCollection.find()) {
            Book book = new Book();
            book.setId(doc.getObjectId("_id").toHexString());
            book.setTitle(doc.getString("title"));
            book.setAuthor(doc.getString("author"));
            book.setPrice(doc.getDouble("price"));
            book.setStock(doc.getInteger("stock"));
            books.add(book);
        }

        return books;
    }

    // Search books by title or author
    public List<Book> searchBooks(String keyword) {
        List<Book> books = new ArrayList<>();
        MongoDatabase db = DBConnection.getDatabase();
        MongoCollection<Document> booksCollection = db.getCollection("books");

        // Match if title or author contains the keyword (case-insensitive approach)
        Document regexQuery = new Document("$regex", ".*" + keyword + ".*").append("$options", "i");
        for (Document doc : booksCollection.find(
                or(eq("title", regexQuery), eq("author", regexQuery))
        )) {
            Book book = new Book();
            book.setId(doc.getObjectId("_id").toHexString());
            book.setTitle(doc.getString("title"));
            book.setAuthor(doc.getString("author"));
            book.setPrice(doc.getDouble("price"));
            book.setStock(doc.getInteger("stock"));
            books.add(book);
        }

        return books;
    }

    // Get a single book by ID
    public Book getBookById(String bookId) {
        MongoDatabase db = DBConnection.getDatabase();
        MongoCollection<Document> booksCollection = db.getCollection("books");
        Document doc = booksCollection.find(eq("_id", new ObjectId(bookId))).first();

        if (doc != null) {
            Book book = new Book();
            book.setId(doc.getObjectId("_id").toHexString());
            book.setTitle(doc.getString("title"));
            book.setAuthor(doc.getString("author"));
            book.setPrice(doc.getDouble("price"));
            book.setStock(doc.getInteger("stock"));
            return book;
        }
        return null;
    }

    // Update book stock (for checkout)
    public void updateStock(String bookId, int newStock) {
        MongoDatabase db = DBConnection.getDatabase();
        MongoCollection<Document> booksCollection = db.getCollection("books");
        booksCollection.updateOne(eq("_id", new ObjectId(bookId)), set("stock", newStock));
    }
}
