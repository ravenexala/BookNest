<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit Book</title>
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
        <h1>Edit Book</h1>

        <form action="editBook" method="POST">
            <c:if test="${not empty sessionScope.loggedUser && sessionScope.loggedUser.role eq 'admin'}">
                <input type="hidden" name="bookId" value="${book.id}" />
                <div class="form-group">
                    <label for="title">Book Title</label>
                    <input type="text" name="title" value="${book.title}" class="form-control" required />
                </div>
                <div class="form-group">
                    <label for="author">Author</label>
                    <input type="text" name="author" value="${book.author}" class="form-control" required />
                </div>
                <div class="form-group">
                    <label for="price">Price</label>
                    <input type="number" name="price" value="${book.price}" class="form-control" required />
                </div>
                <div class="form-group">
                    <label for="stock">Stock</label>
                    <input type="number" name="stock" value="${book.stock}" class="form-control" required />
                </div>
                <div class="form-group">
                    <label for="category">Category</label>
                    <input type="text" name="category" value="${book.category}" class="form-control" required />
                </div>
                <button type="submit" class="btn btn-primary">Save Changes</button>
            </c:if>

            <c:if test="${empty sessionScope.loggedUser || sessionScope.loggedUser.role ne 'admin'}">
                <p>You must be an admin to edit book details.</p>
            </c:if>
        </form>
    </div>

    <!-- Include Footer Section -->
    <footer>
        <%@ include file="footer.jsp" %>
    </footer>
</body>
</html>
