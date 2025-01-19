<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome to BookNest</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="/BookNest/assests/styles.css" rel="stylesheet">
    <style>
        .hero-section {
            background-image: url('https://via.placeholder.com/1920x600/87CEEB/000000?text=Welcome+to+BookNest');
            background-size: cover;
            color: white;
            text-align: center;
            padding: 100px 0;
        }

        .category-btn {
            background-color: #FFA500;
            color: white;
            padding: 15px 30px;
            font-size: 18px;
            margin: 10px;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        .category-btn:hover {
            background-color: #FF4500;
        }

        .category-section {
            padding: 50px 0;
            text-align: center;
        }

        .category-card {
            background-color: #f8f9fa;
            border-radius: 10px;
            padding: 30px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s ease;
        }

        .category-card:hover {
            transform: scale(1.05);
        }

        .category-icon {
            font-size: 40px;
            color: #FFA500;
        }

        .btn-primary {
            background-color: #FF5733;
            border: none;
            font-size: 18px;
            transition: background-color 0.3s ease;
        }

        .btn-primary:hover {
            background-color: #C70039;
        }
    </style>
</head>
<body>

    <!-- Include Header -->
    <%@ include file="header.jsp" %>

    <!-- Hero Section -->
    <section class="hero-section">
        <h1 style="color: #343a40; font-weight: bold;">Welcome to BookNest</h1>
        <p style="color: #555;">Your favorite online bookstore for all genres</p>
        <a href="books" class="btn btn-primary">Browse Books</a>
    </section>

    <!-- Categories Section -->
    <section class="category-section">
        <h2 class="mb-5">Explore by Categories</h2>
        <div class="container">
            <div class="row">
                <!-- Fiction Category -->
                <div class="col-md-4 mb-4">
                    <div class="category-card">
                        <div class="category-icon">📚</div>
                        <h3>Fiction</h3>
                        <p>Explore thrilling, romantic, and mysterious fiction books</p>
                        <a href="books?category=Fiction" class="category-btn">Browse Fiction</a>
                    </div>
                </div>

                <!-- Science Category -->
                <div class="col-md-4 mb-4">
                    <div class="category-card">
                        <div class="category-icon">🔬</div>
                        <h3>Science</h3>
                        <p>Dive into the world of knowledge and exploration with our science books</p>
                        <a href="books?category=Science" class="category-btn">Browse Science</a>
                    </div>
                </div>

                <!-- History Category -->
                <div class="col-md-4 mb-4">
                    <div class="category-card">
                        <div class="category-icon">🕰️</div>
                        <h3>History</h3>
                        <p>Relive the past and uncover historical events and stories</p>
                        <a href="books?category=History" class="category-btn">Browse History</a>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- Include Footer -->
    <%@ include file="footer.jsp" %>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
