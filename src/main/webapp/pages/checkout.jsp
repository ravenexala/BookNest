<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Checkout - BookNest</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="/BookNest/assests/styles.css" rel="stylesheet">
</head>
<body>
    <%@ include file="header.jsp" %> <!-- Include header -->

    <div class="container mt-5">
        <h1 class="text-center mb-4">Checkout</h1>

        <!-- Cart Summary Section -->
        <div class="row">
            <div class="col-md-8">
                <h3>Your Cart</h3>

                <c:if test="${empty cart}">
                    <p>Your cart is empty. Please add items to your cart before proceeding.</p>
                    <a href="/BookNest/books" class="btn btn-primary">Continue Shopping</a>
                </c:if>

                <c:forEach var="item" items="${cart}">
                    <div class="cart-item mb-3">
                        <div class="row">
                            <div class="col-md-8">
                                <h5>${item.book.title}</h5>
                                <p><strong>Quantity:</strong> ${item.quantity}</p>
                                <p><strong>Price:</strong> $${item.book.price} x ${item.quantity} = $${item.getTotalPrice()}</p>
                            </div>
                            <div class="col-md-4 text-right">
                                <form action="cart" method="post">
                                    <input type="hidden" name="bookId" value="${item.book.id}">
                                    <button type="submit" name="action" value="remove" class="btn btn-danger">Remove</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <!-- Summary Section -->
            <div class="col-md-4">
                <h3>Order Summary</h3>
                <ul class="list-group">
                    <c:set var="totalPrice" value="0"/>
                    <c:forEach var="item" items="${cart}">
                        <c:set var="totalPrice" value="${totalPrice + item.getTotalPrice()}"/>
                    </c:forEach>
                    <li class="list-group-item"><strong>Total Price:</strong> $${totalPrice}</li>
                </ul>

                <!-- Form for shipping and payment -->
                <h4>Shipping Address</h4>
                <form action="checkout" method="post">
                    <div class="form-group">
                        <label for="address">Address:</label>
                        <input type="text" id="address" name="address" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="paymentInfo">Payment Info:</label>
                        <input type="text" id="paymentInfo" name="paymentInfo" class="form-control" required>
                    </div>
                    <button type="submit" class="btn btn-success btn-block" style="background-color: #28a745;">Complete Purchase</button>
                </form>
            </div>
        </div>
    </div>

    <%@ include file="footer.jsp" %> <!-- Include footer -->

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
