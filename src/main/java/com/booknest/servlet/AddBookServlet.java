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

        try {
            // Parse price and stock values
            price = Double.parseDouble(request.getParameter("price").trim());
            stock = Integer.parseInt(request.getParameter("stock").trim());
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid price or stock format.");
            return;
        }

        // Handle image file upload
        Part filePart = request.getPart("image");
        String imageFileName = null;
        if (filePart != null) {
            // Generate unique file name to avoid name collisions
            String fileExtension = filePart.getSubmittedFileName().substring(filePart.getSubmittedFileName().lastIndexOf("."));
            imageFileName = UUID.randomUUID().toString() + fileExtension;

            // Define where to store the image
            String uploadDir = getServletContext().getRealPath("/") + "assests/images" + File.separator; // Path to save images
            File uploadDirFile = new File(uploadDir);
            if (!uploadDirFile.exists()) {
                uploadDirFile.mkdir();  // Create directory if it doesn't exist
            }

            // Save the file to the server
            filePart.write(uploadDir + imageFileName);
        }

        // Create book object and add to database
        Book book = new Book(null, title, author, category, price, stock, imageFileName); // Add the image file name to the book object
        String bookId = bookDAO.createBook(book);

        if (bookId != null) {
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("Book added successfully! ID: " + bookId);
            response.sendRedirect("/BookNest/pages/books.jsp");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Failed to add the book.");
        }
    }
}
