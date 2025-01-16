package com.booknest.servlet;

import com.booknest.dao.UserDAO;
import com.booknest.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("pages/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userDAO.authenticate(username, password);

        if (user != null) {
            // Valid user
            HttpSession session = request.getSession();
            session.setAttribute("loggedUser", user);

            if ("admin".equalsIgnoreCase(user.getRole())) {
                // Redirect to admin dashboard
                response.sendRedirect("adminDashboard"); // Replace with your admin page endpoint
            } else if ("user".equalsIgnoreCase(user.getRole())) {
                // Redirect to user homepage
                response.sendRedirect("userHomepage"); // Replace with your user page endpoint
            } else {
                // Role is undefined
                request.setAttribute("errorMessage", "Invalid role for this user.");
                request.getRequestDispatcher("pages/login.jsp").forward(request, response);
            }
        } else {
            // Invalid credentials
            request.setAttribute("errorMessage", "Invalid username or password.");
            request.getRequestDispatcher("pages/login.jsp").forward(request, response);
        }
    }
}
