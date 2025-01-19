<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order History - BookNest</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="/BookNest/assests/styles.css" rel="stylesheet">
</head>
<body>
    <%@ include file="header.jsp" %>

    <div class="container mt-5">
        <h2 class="text-center">Your Order History</h2>

        <!-- Check if there are no orders -->
        <c:if test="${empty orders}">
            <p>Your order history is empty.</p>
        </c:if>

        <!-- Display Orders -->
        <c:forEach var="order" items="${orders}">
            <div class="order-card mb-4 p-3 border">
                <h3>Order ID: ${order.id}</h3>
                <p>Date: ${order.orderDate}</p>

                <!-- Display order items -->
                <div>
                    <h5>Order Items:</h5>
                    <c:forEach var="item" items="${order.orderItems}">
                        <div class="order-item">
                            <p><strong>Book:</strong> ${item.book.title}</p>
                            <p><strong>Author:</strong> ${item.book.author}</p> <!-- Display author name -->
                            <p><strong>Quantity:</strong> ${item.quantity}</p>
                            <p><strong>Price:</strong> $${item.price}</p>
                            <p><strong>Total:</strong> $${item.getTotalPrice()}</p> <!-- Total for the item -->
                        </div>
                    </c:forEach>
                </div>
            </div>
        </c:forEach>

    </div>

    <%@ include file="footer.jsp" %>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
