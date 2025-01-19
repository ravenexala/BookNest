<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet"> <!-- Ensure correct path to CSS -->
    <link href="/BookNest/assests/styles.css" rel="stylesheet">
</head>
<body>
    <!-- Include Header Section -->
    <header>
        <%@ include file="header.jsp" %>
    </header>

    <!-- Admin Dashboard Content -->
    <div class="container admin-container">
        <h1>Admin Dashboard</h1>
        <p>Welcome, Admin!</p>

        <div class="admin-dashboard-cards">
            <!-- Admin Card 1: Add Book -->
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Add New Book</h5>
                    <p class="card-text">Add a new book to the store.</p>
                    <a href="/BookNest/pages/addBook.jsp" class="btn btn-success">Add Book</a>
                </div>
            </div>

            <!-- Admin Card 2: Edit Book -->
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Edit Book</h5>
                    <p class="card-text">Update details of existing books.</p>
                    <a href="/BookNest/pages/editBook.jsp" class="btn btn-success">Edit Book</a>
                </div>
            </div>

            <!-- Admin Card 3: Manage User Profiles -->
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Manage User Profiles</h5>
                    <p class="card-text">View and manage user profiles.</p>
                    <a href="/BookNest/pages/user-management.jsp" class="btn btn-success">Manage Users</a>
                </div>
            </div>

            <!-- Admin Card 4: View Orders -->
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">View Orders</h5>
                    <p class="card-text">View and manage orders from users.</p>
                    <a href="/BookNest/pages/view-orders.jsp" class="btn btn-success">View Orders</a>
                </div>
            </div>
        </div>
    </div>

    <!-- Include Footer Section -->
    <footer>
        <%@ include file="footer.jsp" %>
    </footer>

</body>
</html>
