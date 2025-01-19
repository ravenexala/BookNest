package com.booknest.servlet;

import com.booknest.dao.OrderDAO;
import com.booknest.model.Order;
import com.booknest.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/orderHistory")
public class OrderHistoryServlet extends HttpServlet {

    private OrderDAO orderDAO = new OrderDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User loggedUser = (User) session.getAttribute("loggedUser");

        if (loggedUser == null) {
            // If the user is not logged in, redirect to login page
            response.sendRedirect("login");
            return;
        }

        // Retrieve the orders for the logged-in user
        List<Order> orders = orderDAO.getOrdersByUser(loggedUser.getId());

        // Set the orders as a request attribute
        request.setAttribute("orders", orders);

        // Forward the request to the order history JSP page
        request.getRequestDispatcher("/pages/orderHistory.jsp").forward(request, response);
    }
}
