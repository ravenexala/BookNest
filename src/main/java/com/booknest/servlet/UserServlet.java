package com.booknest.servlet;

import com.booknest.dao.UserDAO;
import com.booknest.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/users")
public class UserServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Check if the logged-in user is an admin
        HttpSession session = request.getSession();
        User loggedUser = (User) session.getAttribute("loggedUser");

        if (loggedUser == null || !"admin".equalsIgnoreCase(loggedUser.getRole())) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Get the list of users from the database
        List<User> users = userDAO.getAllUsers();

        // Set the users attribute to pass it to the JSP
        request.setAttribute("users", users);

        // Forward the request to the user management page
        request.getRequestDispatcher("/pages/user-management.jsp").forward(request, response);
    }
}
