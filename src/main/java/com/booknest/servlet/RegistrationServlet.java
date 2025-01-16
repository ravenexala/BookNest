package com.booknest.servlet;

import com.booknest.dao.UserDAO;
import com.booknest.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Show registration page (JSP)
        request.getRequestDispatcher("pages/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle form submission
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email    = request.getParameter("email");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        boolean isRegistered = userDAO.registerUser(user);
        if (isRegistered) {
            // Registration successful
            response.sendRedirect("login");
        } else {
            // Registration failed (duplicate username or error)
            request.setAttribute("errorMessage", "Registration failed. Username may already exist.");
            request.getRequestDispatcher("pages/register.jsp").forward(request, response);
        }
    }
}
