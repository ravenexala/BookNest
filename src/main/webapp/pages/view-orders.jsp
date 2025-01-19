<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>View Orders - Admin</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet"> <!-- Include updated styles -->
    <link href="/BookNest/assests/styles.css" rel="stylesheet">
</head>
<body>
    <!-- Include Header Section -->
    <header>
        <%@ include file="header.jsp" %>
    </header>

    <!-- Admin Dashboard Content -->
    <div class="container mt-5 admin-container">
        <h1>View Orders</h1>

        <!-- Role check: If the user is not an admin, they will be redirected -->
        <c:if test="${not empty sessionScope.loggedUser && sessionScope.loggedUser.role eq 'admin'}">
            <c:forEach var="order" items="${orders}">
                <div class="order-item">
                    <h3>Order ID: ${order.id}</h3>
                    <p>Status: ${order.status}</p>
                    <form action="update-order-status" method="post">
                        <input type="hidden" name="orderId" value="${order.id}" />
                        <label for="status">Select Status:</label>
                        <select name="status" id="status" class="form-control">
                            <option value="Pending" ${order.status == 'Pending' ? 'selected' : ''}>Pending</option>
                            <option value="Shipped" ${order.status == 'Shipped' ? 'selected' : ''}>Shipped</option>
                            <option value="Delivered" ${order.status == 'Delivered' ? 'selected' : ''}>Delivered</option>
                        </select>
                        <button type="submit" class="btn btn-primary mt-2">Update Status</button>
                    </form>
                </div>
            </c:forEach>
        </c:if>

        <c:if test="${empty sessionScope.loggedUser || sessionScope.loggedUser.role ne 'admin'}">
            <p>You must be an admin to view and manage orders.</p>
        </c:if>
    </div>

    <!-- Include Footer Section -->
    <footer>
        <%@ include file="footer.jsp" %>
    </footer>
</body>
</html>
