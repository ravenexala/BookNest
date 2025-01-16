package com.booknest.servlet;

import com.booknest.dao.BookDAO;
import com.booknest.model.Book;
import com.booknest.model.CartItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    private BookDAO bookDAO = new BookDAO();

    @SuppressWarnings("unchecked")
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Show cart page
        HttpSession session = request.getSession();
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }
        request.setAttribute("cart", cart);
        request.getRequestDispatcher("pages/cart.jsp").forward(request, response);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        if ("add".equals(action)) {
            String bookId = request.getParameter("bookId");
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            Book book = bookDAO.getBookById(bookId);
            if (book != null && quantity > 0) {
                boolean found = false;
                for (CartItem item : cart) {
                    if (item.getBook().getId().equals(book.getId())) {
                        item.setQuantity(item.getQuantity() + quantity);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    cart.add(new CartItem(book, quantity));
                }
            }
            session.setAttribute("cart", cart);
            response.sendRedirect("cart");

        } else if ("remove".equals(action)) {
            String bookId = request.getParameter("bookId");
            cart.removeIf(item -> item.getBook().getId().equals(bookId));
            session.setAttribute("cart", cart);
            response.sendRedirect("cart");
        }
    }
}
