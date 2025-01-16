package com.booknest.servlet;

import com.booknest.dao.BookDAO;
import com.booknest.dao.OrderDAO;
import com.booknest.model.CartItem;
import com.booknest.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    private OrderDAO orderDAO = new OrderDAO();
    private BookDAO bookDAO = new BookDAO();

    @SuppressWarnings("unchecked")
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User loggedUser = (User) session.getAttribute("loggedUser");

        if (loggedUser == null) {
            response.sendRedirect("login");
            return;
        }

        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null || cart.isEmpty()) {
            response.sendRedirect("books");
            return;
        }

        // Create order in DB
        String orderId = orderDAO.createOrder(loggedUser.getId(), cart);

        // Update stock
        if (orderId != null) {
            for (CartItem item : cart) {
                int newStock = item.getBook().getStock() - item.getQuantity();
                bookDAO.updateStock(item.getBook().getId(), newStock);
            }
            // Clear the cart
            session.removeAttribute("cart");
            response.sendRedirect("orderHistory");
        } else {
            request.setAttribute("errorMessage", "Failed to create order. Please try again.");
            request.getRequestDispatcher("pages/cart.jsp").forward(request, response);
        }
    }
}
