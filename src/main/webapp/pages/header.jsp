<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header class="bg-dark text-white p-3">
    <div class="container">
        <div class="d-flex justify-content-between align-items-center">
            <!-- Logo and Title with clickable link -->
            <a href="/BookNest" class="d-flex align-items-center text-white text-decoration-none">
                <img src="/BookNest/assests/booknest-logo.png" alt="BookNest Logo" style="width: 70px; height: auto; margin-right: 15px;"> <!-- Adjusted size -->
                <h1 class="m-0" style="font-size: 28px; font-weight: bold;">BookNest</h1> <!-- Increased font size and made bold -->
            </a>

            <!-- Navigation Menu -->
            <nav>
                <ul class="list-unstyled d-flex mb-0">
                    <!-- Common menu items visible to all users -->
                    <li><a href="/BookNest" class="text-white mx-3">Home</a></li>
                    <li><a href="/BookNest/books" class="text-white mx-3">Available Books</a></li>
                    <li><a href="/BookNest/cart" class="text-white mx-3">Cart</a></li>
                    <li><a href="/BookNest/aboutus" class="text-white mx-3">About Us</a></li>

                    <!-- Check if the user is logged in and their role -->
                    <c:choose>
                        <c:when test="${not empty sessionScope.loggedUser}">
                            <!-- Admin Role Menu -->
                            <c:if test="${sessionScope.loggedUser.role eq 'admin'}">
                                <li><a href="/BookNest/pages/adminDashboard.jsp" class="text-white mx-3">Admin Dashboard</a></li>
                                <li><a href="/BookNest/logout" class="text-white mx-3">Logout</a></li>
                            </c:if>

                            <!-- Regular User Menu -->
                            <c:if test="${sessionScope.loggedUser.role eq 'user'}">
                                <li><a href="/BookNest/orderHistory" class="text-white mx-3">Order History</a></li>
                                <li><a href="/BookNest/logout" class="text-white mx-3">Logout</a></li>
                            </c:if>
                        </c:when>
                        <c:otherwise>
                            <!-- Show Login if user is not logged in -->
                            <li><a href="/BookNest/login" class="text-white mx-3">Login</a></li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </nav>
        </div>
    </div>
</header>
