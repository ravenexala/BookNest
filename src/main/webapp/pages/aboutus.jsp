<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>About Us - BookNest</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="/BookNest/assests/styles.css" rel="stylesheet"> <!-- Link to your CSS -->
</head>
<body>
    <%@ include file="header.jsp" %> <!-- Include header -->

    <div class="container mt-5">
        <h1 class="text-center mb-4">About Us</h1>

        <!-- Our Mission Section -->
        <div class="row">
            <div class="col-md-6">
                <h2 style="font-size: 2.2em; font-weight: bold;">Our Mission</h2>
                <p style="font-size: 1.3em; text-align: justify;"> <!-- Increased font size for the mission description -->
                    At BookNest, we believe that books have the power to transport us to new worlds, open our minds to fresh perspectives, and provide an escape from the everyday. Our mission is to make the finest collection of books accessible to readers of all ages and backgrounds. Whether you're a casual reader or a passionate bookworm, we have something for you.
                </p>
                <p style="font-size: 1.3em; text-align: justify;"> <!-- Increased font size for the mission description -->
                    We work tirelessly to offer a wide variety of genres, from the latest bestsellers to hidden gems and timeless classics. Our goal is to create a space where you can find books that resonate with your unique tastes and preferences.
                </p>
            </div>
            <div class="col-md-6">
                <img src="/BookNest/assests/about-us-pic.jpg" alt="Our Mission" class="img-fluid rounded" style="max-width: 85%; height: auto; margin-top: 25px;">
            </div>
        </div>

        <!-- Why Choose Us Section -->
        <div class="row mt-5">
            <div class="col">
                <h3 style="font-size: 1.8em;">Why Choose Us?</h3>
                <ul style="font-size: 1.2em;">
                    <li><strong>Wide Selection:</strong> We have a vast catalog of books ranging from fiction, non-fiction, self-help, to educational materials.</li>
                    <li><strong>Fast & Reliable Delivery:</strong> Enjoy quick and safe delivery of your books, directly to your door.</li>
                    <li><strong>Secure Shopping Experience:</strong> Your privacy and safety are our top priority. Shop with confidence knowing your information is protected.</li>
                    <li><strong>Customer-Centered Service:</strong> Our dedicated customer support team is here to assist you at every step of your journey with us.</li>
                </ul>
            </div>
        </div>

        <!-- Join Our Reading Community Section -->
        <div class="mt-5 text-center">
            <h4 style="font-size: 1.8em;">Join Our Reading Community</h4>
            <p style="font-size: 1.2em;">At BookNest, we're more than just a bookstore—we're a community. Follow us on social media, join our newsletter, and be a part of a growing community of book lovers!</p>
            <a href="/BookNest/books" class="btn" style="background-color: #dc3545; color: white; font-size: 1.2em;">Explore Our Collection</a>
        </div>
    </div>

    <%@ include file="footer.jsp" %> <!-- Include footer -->

</body>
</html>
