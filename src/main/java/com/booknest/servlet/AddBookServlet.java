package com.booknest.servlet;

import com.booknest.dao.BookDAO;
import com.booknest.model.Book;
import com.booknest.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.http.Part;

@WebServlet("/books/add")
public class AddBookServlet extends HttpServlet {

    private final BookDAO bookDAO = new BookDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/pages/addBook.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User loggedUser = (User) session.getAttribute("loggedUser");

        // Check if the logged user is an admin
        if (loggedUser == null || !"admin".equalsIgnoreCase(loggedUser.getRole())) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Only admins can add books.");
            return;
        }

        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String category = request.getParameter("category");
        double price;
        int stock;

        // Validate price and stock
        try {
            String priceStr = request.getParameter("price").trim();
            String stockStr = request.getParameter("stock").trim();

            if (priceStr.isEmpty() || stockStr.isEmpty()) {
                throw new NumberFormatException("Price or stock cannot be empty.");
            }

            price = Double.parseDouble(priceStr);
            stock = Integer.parseInt(stockStr);
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("error", "Invalid price or stock format.");
            request.getRequestDispatcher("/pages/addBook.jsp").forward(request, response);
            return;
        }

        // Handle image file upload
        Part filePart = request.getPart("image");
        String imageFileName = null;

        if (filePart != null && filePart.getSize() > 0) {
            String fileExtension = filePart.getSubmittedFileName().substring(
                    filePart.getSubmittedFileName().lastIndexOf(".")
            );
            if (!fileExtension.matches("\\.(jpg|jpeg|png)$")) {
                request.setAttribute("error", "Invalid image format. Only JPG and PNG are allowed.");
                request.getRequestDispatcher("/pages/addBook.jsp").forward(request, response);
                return;
            }

            // Generate unique file name
            imageFileName = UUID.randomUUID().toString() + fileExtension;

            // Define the path to save the image
            String uploadDir = getServletContext().getRealPath("/") + "assets/images" + File.separator;
            File uploadDirFile = new File(uploadDir);
            if (!uploadDirFile.exists() && !uploadDirFile.mkdirs()) {
                request.setAttribute("error", "Failed to create directory for image upload.");
                request.getRequestDispatcher("/pages/addBook.jsp").forward(request, response);
                return;
            }

            filePart.write(uploadDir + imageFileName);
        }

        // Create book object and add to database
        Book book = new Book(null, title, author, category, price, stock, imageFileName);
        String bookId = bookDAO.createBook(book);

        if (bookId != null) {
            response.sendRedirect("/BookNest/pages/books.jsp?success=true");
        } else {
            request.setAttribute("error", "Failed to add the book. Please try again.");
            request.getRequestDispatcher("/pages/addBook.jsp").forward(request, response);
        }
    }
}