document.addEventListener("DOMContentLoaded", function () {
    fetch('http://52.69.33.14/api/1.0/marketing/campaigns')
        .then(response => {
            if (!response.ok) {
                throw new Error('Response not ok')
            }
            return response.json();
        })
        .then(json => {
            const campaignDiv = document.getElementById('campaign')
            const formattedStory = json.data[0].story.split(' ').join('<br>');
            campaignDiv.innerHTML = `
                <a href="product.html?id=${json.data[0].product_id}"><img src="${json.data[0].picture}" data-id="${json.data[0].product_id}"></a> 
                <div class="campaign-text">
                    <p>${formattedStory}</p>
                </div>
            `

        })
        .catch(error => {
            console.error('Error fetch response', error);
        })
})

document.addEventListener("DOMContentLoaded", function () {
    const navLinks = document.querySelectorAll('.nav-links a');
    const productContainer = document.querySelector('.product-container');

    function fetchProducts(category) {
        const url = category ? `http://52.69.33.14/api/1.0/products/${category}` : 'http://52.69.33.14/api/1.0/products/all';

        fetch(url)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Response not ok');
                }
                return response.json();
            })
            .then(json => {
                productContainer.innerHTML = ''; // Clear existing content

                json.data.forEach(product => {
                    const productDiv = document.createElement('div');
                    productDiv.classList.add('product');

                    // Product image
                    const link = document.createElement('a');
                    link.href = `product.html?id=${product.id}`

                    const img = document.createElement('img');
                    img.src = product.main_image;
                    img.alt = product.title;
                    img.setAttribute('data-id', product.id); // Set product ID
                    img.classList.add('product-img');
                    link.appendChild(img);
                    productDiv.appendChild(link);

                    // Color blocks
                    const colorBlocksDiv = document.createElement('div');
                    colorBlocksDiv.classList.add('color-blocks');
                    product.colors.forEach(color => {
                        const colorBlock = document.createElement('div');
                        colorBlock.classList.add('color-block');
                        colorBlock.style.backgroundColor = color.code;
                        colorBlocksDiv.appendChild(colorBlock);
                    });
                    productDiv.appendChild(colorBlocksDiv);

                    // Product name
                    const productName = document.createElement('p');
                    productName.classList.add('product-name');
                    productName.textContent = product.title;
                    productDiv.appendChild(productName);

                    // Product price
                    const productPrice = document.createElement('p');
                    productPrice.classList.add('product-price');
                    productPrice.textContent = `TWD.${product.price}`;
                    productDiv.appendChild(productPrice);

                    productContainer.appendChild(productDiv);
                });

                // Add click event listeners to product images
                document.querySelectorAll('.product-img').forEach(img => {
                    img.addEventListener('click', function (event) {
                        const id = event.target.getAttribute('data-id');
                        window.location.href = `product.html?id=${id}`;
                    });
                });
            })
            .catch(error => {
                console.error('Error fetching products:', error);
            });
    }

    const urlParams = new URLSearchParams(window.location.search);
    const initialCategory = urlParams.get('category');
    if (initialCategory) {
        fetchProducts(initialCategory);
    } else {
        fetchProducts();
    }

    navLinks.forEach(link => {
        link.addEventListener('click', function (event) {
            event.preventDefault();

            const category = link.getAttribute('data-category');
            const newUrl = `index.html?category=${category}`;
            history.pushState({category}, '', newUrl); // Update URL without reloading the page

            fetchProducts(category);
        });
    });

    // Handle browser navigation (back/forward buttons)
    window.addEventListener('popstate', function (event) {
        const category = event.state ? event.state.category : null;
        if (category) {
            fetchProducts(category);
        } else {
            fetchProducts();
        }
    });
});
