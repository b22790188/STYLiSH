function addProduct() {
    const productList = document.getElementById("product-list");
    const productTemplate = document.getElementById("product-template").content.cloneNode(true);
    productList.appendChild(productTemplate);
}

function removeProduct(button) {
    button.parentElement.remove();
}
