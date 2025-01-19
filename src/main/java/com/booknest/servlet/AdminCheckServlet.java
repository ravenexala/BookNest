package com.booknest.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/*")
public class AdminCheckServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // Check if session exists and the user has an admin role
        if (session == null || !"admin".equalsIgnoreCase((String) session.getAttribute("role"))) {
            // Redirect to login page or show an error message
            response.sendRedirect("login"); // You can change this to redirect to an error page
            return;
        }

        // Proceed with the request if the user is an admin
        // Add the admin-specific functionality or page redirection
        request.getRequestDispatcher("/pages/adminDashboard.jsp").forward(request, response);
    }
}
