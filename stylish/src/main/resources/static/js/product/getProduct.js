const productContainer = document.querySelector('.product-container');
const detailedDescription = document.querySelector('.detailed-description');
const productGallery = document.querySelector('.product-gallery');

const productId = new URLSearchParams(window.location.search).get('id');

let productData = null;
let selectedColor = null;
let selectedSize = null;
let currentMaxStock = 0;

document.addEventListener('DOMContentLoaded', () => {
    const payButton = document.querySelector('#credit-card-form button[type="submit"]');
    if (payButton) {
        payButton.addEventListener('click', event => {
            handlePayment(event);
        });
    }
})


if (productId) {
    fetch(`http://52.69.33.14/api/1.0/products/details?id=${productId}`)
        .then(response => response.json())
        .then(json => {
            productData = json.data;
            updateProductDisplay();
        })
        .catch(error => {
            console.error('Error fetching product details:', error);
        })
} else {
    console.error('No product ID found in URL');
}

function updateProductDisplay() {
    productContainer.innerHTML = `
        <div class="product-image">
            <img src="${productData.main_image}" alt="Product Image">
        </div>
        <div class="product-details">
            <h1>${productData.title}</h1>
            <p>產品編號: ${productData.id}</p>
            <p class="price">TWD. ${productData.price}</p>
            <div class="colors">
                ${productData.colors.map(color => `<div style="background-color: ${color.code};" data-color="${color.code}"></div>`).join('')}
            </div>
            <div class="sizes">
                ${productData.sizes.map(size => `<div data-size="${size}">${size}</div>`).join('')}
            </div>
            <div class="quantity-selector">
                <button class="quantity-btn" id="decrease">-</button>
                <input type="number" id="quantity" value="1" min="1" readonly/>
                <button class="quantity-btn" id="increase">+</button>
            </div>
            <div id="add-to-cart-btn" class="add-to-cart disabled">請選擇尺寸</div>
            <div class="product-note">
                <p>${productData.note}</p>
            </div>
            <div class="product-texture">
                <p>${productData.texture}</p>
            </div>
            <div class="product-place">
                <p>${productData.place}</p>
            </div>
        </div>
    `;

    detailedDescription.innerHTML = `
        <div class="title-and-divider">
            <h2>細部說明 </h2>
            <div class="divider"></div>
        </div>
        <p class="description-text">${productData.description}</p>
    `;

    productGallery.innerHTML = productData.images.map(imageUrl => `
        <img src="${imageUrl}" alt="Gallery Image">
    `).join('');

    const colors = document.querySelectorAll('.product-details .colors div');
    const sizes = document.querySelectorAll('.product-details .sizes div');
    const increaseButton = document.getElementById('increase');
    const decreaseButton = document.getElementById('decrease');
    const quantity = document.getElementById('quantity');
    const addToCartButton = document.querySelector('.add-to-cart');


    handleAddToCartClicked(addToCartButton);
    handleColorSelection(colors);
    handleSizeSelection(sizes);
    initializeQuantitySelector(quantity, increaseButton, decreaseButton);

    //select first color when page is loaded
    colors[0].click();
}


function displayPaymentError(message) {
    const errorElement = document.getElementById('payment-error');
    errorElement.textContent = message;
    errorElement.style.display = 'block';
}

function handlePayment(event) {
    event.preventDefault()

    window.getTappayPrime((prime) => {
        if (prime) {
            sendOrderToServer(prime);
        } else {
            console.error('Failed to get prime');
            displayPaymentError('Something worn during payment!')
        }
    })
}

function sendOrderToServer(prime) {
    const orderData = {
        prime: prime,
        order: {
            shipping: "delivery",
            payment: "credit_card",
            subtotal: productData.price,
            freight: 30,
            total: productData.price + 30,
            recipient: {
                name: "Luke",
                phone: "0987654321",
                email: "luke@gmail.com",
                address: "市政府站",
                time: "morning"
            },
            list: [
                {
                    id: productData.id,
                    name: productData.title,
                    price: productData.price,
                    color: {
                        name: productData.colors.find(c => c.code === selectedColor).name,
                        code: selectedColor
                    },
                    size: selectedSize,
                    qty: parseInt(document.getElementById('quantity').value)
                },
            ]
        }
    }

    fetch(`http://52.69.33.14/api/1.0/order/checkout`, {
        method: 'POST',
        headers: {
            'Authorization': `Bearer ${localStorage.getItem('jwt')}`,
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(orderData)
    })
        .then(response => {
            if (!response.ok) {
                new Error(response.json().error);
            }
            return response.json();
        })
        .then(json => {
            const orderNumber = json.data.number;
            console.log('payment success your orderNumber:', orderNumber);
            window.location.href = 'thankyou.html'
        })
        .catch(error => {
            displayPaymentError('Payment failed! Please try again');
        })
}

function handleAddToCartClicked(elements) {
    elements.addEventListener('click', () => {
        if (elements.classList.contains('disabled')) {
            return;
        }

        const jwt = localStorage.getItem('jwt');
        if (!jwt || isTokenExpired(jwt)) {
            localStorage.removeItem('jwt');
            const currentUrl = window.location.href;
            window.location.href = `login.html?redirect=${encodeURIComponent(currentUrl)}`;
        } else {
            //scroll to credit card form
            const creditCardForm = document.getElementById('credit-card-form');
            if (creditCardForm) {
                creditCardForm.scrollIntoView({behavior: "smooth"});
            }
        }
    })
}

function handleColorSelection(elements) {
    elements.forEach(el => {
        el.addEventListener('click', () => {
            if (!el.classList.contains('disabled')) {
                elements.forEach(e => e.classList.remove('selected'));
                el.classList.add('selected');
                selectedColor = el.dataset.color;
                updateAvailableSizes();
                updateMaxStock();
                updateAddToCartButton();
            }
        });
    });
}

function handleSizeSelection(elements) {
    elements.forEach(el => {
        el.addEventListener('click', () => {
            if (!el.classList.contains('disabled')) {
                elements.forEach(e => e.classList.remove('selected'));
                el.classList.add('selected');
                selectedSize = el.dataset.size;
                updateAvailableColors();
                updateMaxStock();
                updateAddToCartButton();
            }
        });
    });
}

function updateAvailableColors() {
    const colors = document.querySelectorAll('.product-details .colors div');
    colors.forEach(colorEl => {
        const hasStock = productData.variants.some(v =>
            v.size === selectedSize && v.color_code === colorEl.dataset.color && v.stock > 0
        );
        colorEl.classList.toggle('disabled', !hasStock);
    });
}

function updateAvailableSizes() {
    const sizes = document.querySelectorAll('.product-details .sizes div');
    sizes.forEach(sizeEl => {
        const hasStock = productData.variants.some(v =>
            v.color_code === selectedColor && v.size === sizeEl.dataset.size && v.stock > 0
        );
        sizeEl.classList.toggle('disabled', !hasStock);
    });
}

function updateMaxStock() {
    if (selectedColor && selectedSize) {
        const variant = productData.variants.find(v => v.color_code === selectedColor && v.size === selectedSize);
        if (variant) {
            currentMaxStock = variant.stock;
            const quantityInput = document.getElementById('quantity');
            if (parseInt(quantityInput.value) > currentMaxStock) {
                quantityInput.value = currentMaxStock;
            }
        }
    }
}

function initializeQuantitySelector(inputElement, increaseButton, decreaseButton) {
    increaseButton.addEventListener('click', () => {
        updateQuantity(inputElement, 1);
    });

    decreaseButton.addEventListener('click', () => {
        updateQuantity(inputElement, -1);
    });
}

function updateQuantity(inputElement, change) {
    let currentValue = parseInt(inputElement.value, 10);
    let newValue = currentValue + change;

    if (newValue >= 1 && newValue <= currentMaxStock) {
        inputElement.value = newValue;
    }
}

function updateAddToCartButton() {
    const addToCartButton = document.querySelector('.add-to-cart');
    if (selectedColor && selectedSize) {
        addToCartButton.classList.remove('disabled');
        addToCartButton.textContent = '購買';
    } else {
        addToCartButton.classList.add('disabled');
        addToCartButton.textContent = '請選擇尺寸';
    }
}

function parseJwt(token) {
    const base64Url = token.split('.')[1];
    const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    const jsonPayload = decodeURIComponent(atob(base64).split('').map(function (c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));

    return JSON.parse(jsonPayload);
}

function isTokenExpired(token) {
    if (!token) {
        return true;
    }

    const decodedToken = parseJwt(token);
    const currentTime = Date.now() / 1000;


    return decodedToken.exp < currentTime;
}



