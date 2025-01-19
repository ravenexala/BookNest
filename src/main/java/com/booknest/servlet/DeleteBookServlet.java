package com.booknest.servlet;

import com.booknest.dao.BookDAO;
import com.booknest.model.User;

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

        HttpSession session = request.getSession();
        User loggedUser = (User) session.getAttribute("loggedUser");

        if (loggedUser == null || !"admin".equalsIgnoreCase(loggedUser.getRole())) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Only admins can delete books.");
            return;
        }

        String bookId = request.getParameter("bookId");
        boolean success = bookDAO.deleteBook(bookId);

        if (success) {
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("Book deleted successfully.");
            response.sendRedirect("/BookNest/pages/adminDashboard.jsp");
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Failed to delete the book. Ensure the ID is valid.");
        }
    }
}
