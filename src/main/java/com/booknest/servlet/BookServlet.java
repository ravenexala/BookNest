package com.booknest.servlet;

import com.booknest.dao.BookDAO;
import com.booknest.model.Book;
import com.google.gson.Gson;  // Import Gson to convert Java objects to JSON

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/books")
public class BookServlet extends HttpServlet {

    private BookDAO bookDAO = new BookDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get search parameter if available
        String search = request.getParameter("search");
        List<Book> books;

        // Fetch books based on search or get all books
        if (search != null && !search.trim().isEmpty()) {
            books = bookDAO.searchBooks(search);
        } else {
            books = bookDAO.getAllBooks();
        }

        // Check if the request expects JSON (for API)
        String acceptHeader = request.getHeader("Accept");
        if (acceptHeader != null && acceptHeader.equals("application/json")) {
            // If the request expects JSON, return the list of books in JSON format
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            // Convert the books list into JSON and send it in the response
            Gson gson = new Gson();
            String booksJson = gson.toJson(books);
            response.getWriter().write(booksJson);

        } else {
            // If not an API request, forward to the JSP page to display the books
            request.setAttribute("books", books);
            request.getRequestDispatcher("pages/books.jsp").forward(request, response);
        }
    }
}
