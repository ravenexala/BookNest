package com.booknest.servlet;

import com.booknest.dao.BookDAO;
import com.booknest.model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/books/update")
public class UpdateBookServlet extends HttpServlet {

    private final BookDAO bookDAO = new BookDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Check if the logged-in user is an admin
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");
        if (!"admin".equalsIgnoreCase(role)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Only admins can update books.");
            return;
        }

        // Retrieve book details from the request
        String bookId = request.getParameter("bookId");
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String category = request.getParameter("category");
        double price;
        int stock;

        try {
            price = Double.parseDouble(request.getParameter("price"));
            stock = Integer.parseInt(request.getParameter("stock"));
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid price or stock format.");
            return;
        }

        // Create an updated book object
        Book updatedBook = new Book(bookId, title, author, category, price, stock);

        // Update the book in the database
        boolean success = bookDAO.updateBook(bookId, updatedBook);

        if (success) {
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("Book updated successfully.");
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Failed to update the book. Ensure the ID is valid.");
        }
    }
}
