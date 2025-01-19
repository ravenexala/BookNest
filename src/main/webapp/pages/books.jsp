<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Available Books</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="/BookNest/assests/styles.css" rel="stylesheet">
</head>
<body>
    <%@ include file="header.jsp" %>

    <div class="container mt-5">
        <h2 class="text-center">Available Books</h2>

        <form action="books" method="get" class="form-inline mb-4">
            <input type="text" name="search" class="form-control mr-2" placeholder="Search books..."/>
            <button type="submit" class="btn btn-primary">Search</button>
        </form>

        <div class="row">
            <!-- Ensure books attribute is being passed properly -->
            <c:forEach var="book" items="${books}">
                <div class="col-md-4 mb-4">
                    <div class="card">
                        <img class="card-img-top" src="https://via.placeholder.com/150" alt="Book image">
                        <div class="card-body">
                            <h5 class="card-title">${book.title}</h5>
                            <p class="card-text">Author: ${book.author}</p>
                            <p class="card-text">Category: ${book.category}</p>
                            <p class="card-text">Price: $${book.price}</p>
                            <p class="card-text">Stock: ${book.stock}</p>
                            <form action="cart" method="post">
                                <input type="hidden" name="bookId" value="${book.id}"/>
                                <input type="number" name="quantity" min="1" max="${book.stock}" class="form-control mb-2" placeholder="Quantity"/>
                                <button type="submit" name="action" value="add" class="btn btn-success btn-block">Add to Cart</button>
                            </form>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <%@ include file="footer.jsp" %>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
