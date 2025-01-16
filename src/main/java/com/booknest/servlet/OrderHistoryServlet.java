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
            response.sendRedirect("login");
            return;
        }

        List<Order> orders = orderDAO.getOrdersByUser(loggedUser.getId());
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("pages/orderHistory.jsp").forward(request, response);
    }
}
