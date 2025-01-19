<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Management - Admin</title>
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
        <h1>User Management</h1>

        <c:if test="${not empty sessionScope.loggedUser && sessionScope.loggedUser.role eq 'admin'}">
            <c:forEach var="user" items="${users}">
                <div>
                    <p>Username: ${user.username}</p>
                    <p>Email: ${user.email}</p>
                    <form action="edit-user" method="get">
                        <input type="hidden" name="userId" value="${user.id}" />
                        <button type="submit">Edit</button>
                    </form>
                    <form action="delete-user" method="post">
                        <input type="hidden" name="userId" value="${user.id}" />
                        <button type="submit">Delete</button>
                    </form>
                </div>
            </c:forEach>
        </c:if>

        <c:if test="${empty sessionScope.loggedUser || sessionScope.loggedUser.role ne 'admin'}">
            <p>You must be an admin to manage users.</p>
        </c:if>
    </div>

    <!-- Include Footer Section -->
    <footer>
        <%@ include file="footer.jsp" %>
    </footer>
</body>
</html>
