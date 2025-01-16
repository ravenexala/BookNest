package com.booknest.servlet;

import com.booknest.dao.BookDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/books/delete")
public class DeleteBookServlet extends HttpServlet {

    private final BookDAO bookDAO = new BookDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Check if the logged-in user is an admin
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");
        if (!"admin".equalsIgnoreCase(role)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Only admins can delete books.");
            return;
        }

        // Retrieve the book ID from the request
        String bookId = request.getParameter("bookId");

        // Delete the book from the database
        boolean success = bookDAO.deleteBook(bookId);

        if (success) {
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("Book deleted successfully.");
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Failed to delete the book. Ensure the ID is valid.");
        }
    }
}
