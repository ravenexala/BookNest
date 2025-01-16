package com.booknest.servlet;

import com.booknest.dao.BookDAO;
import com.booknest.model.Book;

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
        String search = request.getParameter("search");
        List<Book> books;
        if (search != null && !search.trim().isEmpty()) {
            books = bookDAO.searchBooks(search);
        } else {
            books = bookDAO.getAllBooks();
        }

        request.setAttribute("books", books);
        request.getRequestDispatcher("pages/books.jsp").forward(request, response);
    }
}
