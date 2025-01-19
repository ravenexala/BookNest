// DOM Elements
const bookList = document.getElementById('book-list');
const searchForm = document.getElementById('searchForm');
const searchInput = document.getElementById('searchInput');
const cartCount = document.getElementById('cart-count');

// Cart Data (mocking local storage or session)
let cart = JSON.parse(localStorage.getItem('cart')) || [];

// Display Cart Count
function updateCartCount() {
    cartCount.textContent = cart.length;
}

// Fetch Books
function fetchBooks(search = '') {
    const url = `http://localhost:8080/books?search=${search}`;
    fetch(url)
        .then(response => response.json())
        .then(books => {
            bookList.innerHTML = '';
            books.forEach(book => {
                const bookCard = `
                    <div class="col-md-4">
                        <div class="book-card">
                            <img src="book-image-placeholder.jpg" alt="Book Image">
                            <h5>${book.title}</h5>
                            <p>Author: ${book.author}</p>
                            <p>Category: ${book.category}</p>
                            <p class="price">$${book.price}</p>
                            <p class="stock">${book.stock} in stock</p>
                            <button onclick="addToCart('${book.id}')">Add to Cart</button>
                        </div>
                    </div>
                `;
                bookList.innerHTML += bookCard;
            });
        });
}

// Add Book to Cart
function addToCart(bookId) {
    const bookInCart = cart.find(item => item.bookId === bookId);
    if (bookInCart) {
        bookInCart.quantity++;
    } else {
        cart.push({ bookId, quantity: 1 });
    }
    localStorage.setItem('cart', JSON.stringify(cart));
    updateCartCount();
}

// Search Books
searchForm.addEventListener('submit', function(event) {
    event.preventDefault();
    fetchBooks(searchInput.value);
});

// Initial Fetch
fetchBooks();
updateCartCount();
