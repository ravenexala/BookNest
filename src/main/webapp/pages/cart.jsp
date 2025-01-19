<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Cart - BookNest</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="/BookNest/assests/styles.css" rel="stylesheet">
</head>
<body>
    <%@ include file="header.jsp" %> <!-- Include header -->

    <div class="container mt-5">
        <h1>Your Cart</h1>

        <!-- Cart Empty Check -->
        <c:if test="${empty cart}">
            <p>Your cart is empty. Start adding books to your cart!</p>
            <a href="/BookNest/books" class="btn btn-primary">Browse Books</a>
        </c:if>

        <!-- Display Cart Items -->
        <c:if test="${not empty cart}">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Book Title</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Total</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${cart}">
                        <tr>
                            <td>${item.book.title}</td>
                            <td>${item.quantity}</td>
                            <td>$${item.book.price}</td>
                            <td>$${item.getTotalPrice()}</td>
                            <td>
                                <!-- Remove Item from Cart -->
                                <form action="cart" method="post" class="d-inline">
                                    <input type="hidden" name="bookId" value="${item.book.id}" />
                                    <button type="submit" name="action" value="remove" class="btn btn-danger btn-sm">Remove</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <!-- Proceed to Checkout -->
            <div class="text-right">
                <!-- Form to submit POST request to checkout servlet -->
                <form action="checkout" method="post">
                    <button type="submit" class="btn btn-success">Proceed to Checkout</button>
                </form>
            </div>
        </c:if>

    </div>

    <%@ include file="footer.jsp" %> <!-- Include footer -->

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
