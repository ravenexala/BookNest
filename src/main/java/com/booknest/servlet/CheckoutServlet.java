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

        // Fetch shipping address and payment info from the form
        String address = request.getParameter("address");
        String paymentInfo = request.getParameter("paymentInfo");

        // Ensure that address and payment info are provided
        if (address == null || paymentInfo == null || address.isEmpty() || paymentInfo.isEmpty()) {
            request.setAttribute("errorMessage", "Address and payment information are required.");
            request.getRequestDispatcher("pages/checkout.jsp").forward(request, response);
            return;
        }

        // Create order in DB
        String orderId = orderDAO.createOrder(loggedUser.getId(), cart, address, paymentInfo);

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
            request.getRequestDispatcher("pages/checkout.jsp").forward(request, response);
        }
    }
}
