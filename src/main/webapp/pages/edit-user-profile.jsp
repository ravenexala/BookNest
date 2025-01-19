<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit User Profile</title>
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
        <h1>Edit User Profile</h1>

        <c:if test="${not empty sessionScope.loggedUser && sessionScope.loggedUser.role eq 'admin'}">
            <form action="update-user" method="post">
                <input type="hidden" name="userId" value="${user.id}" />
                <label for="username">Username</label>
                <input type="text" name="username" value="${user.username}" required />
                <label for="email">Email</label>
                <input type="email" name="email" value="${user.email}" required />
                <button type="submit" class="btn btn-primary">Save Changes</button>
            </form>
        </c:if>

        <c:if test="${empty sessionScope.loggedUser || sessionScope.loggedUser.role ne 'admin'}">
            <p>You must be an admin to edit user profiles.</p>
        </c:if>
    </div>

    <!-- Include Footer Section -->
    <footer>
        <%@ include file="footer.jsp" %>
    </footer>
</body>
</html>
