package com.booknest.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Invalidate the session if it exists
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // Invalidates the session and removes all session attributes
        }
        // Redirect to the login page after logging out
        response.sendRedirect("/BookNest/login"); // Ensure correct redirection after logout
    }
}
