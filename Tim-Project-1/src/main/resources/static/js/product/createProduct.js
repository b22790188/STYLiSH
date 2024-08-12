let form = document.getElementById("product_form");
let addVariantButton = document.getElementById("add_variant");
let addColorButton = document.getElementById("add_color");
let addSizeButton = document.getElementById("add_size");
let variantTableBody = document.querySelector("#variant_table tbody");
let colorTableBody = document.querySelector("#color_table tbody");
let sizeDiv = document.getElementById("size_div");
let variantRowCount = 1;
let colorRowCount = 1;
let sizeRowCount = 1;

form.addEventListener("submit", (e) => {
    e.preventDefault();

    const formData = new FormData(form);

    console.log(formData)

    fetch('/admin/product', {
        method: 'POST',
        body: formData
    }).then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
})

addVariantButton.addEventListener("click", (e) => {
    e.preventDefault();

    let variantRow = document.createElement("tr");
    let td1 = document.createElement("td");
    let td2 = document.createElement("td");
    let td3 = document.createElement("td");

    let colorCodeInput = document.createElement("input");
    colorCodeInput.type = "text";
    colorCodeInput.id = `variant_color_code_${variantRowCount}`;
    colorCodeInput.name = `variants[${variantRowCount}].colorCode`;

    let sizeInput = document.createElement("input");
    sizeInput.type = "text";
    sizeInput.id = `variant_size_${variantRowCount}`;
    sizeInput.name = `variants[${variantRowCount}].size`;

    let stockInput = document.createElement("input");
    stockInput.type = "text";
    stockInput.id = `variant_stock_${variantRowCount}`;
    stockInput.name = `variants[${variantRowCount}].stock`;

    td1.append(colorCodeInput);
    td2.append(sizeInput);
    td3.append(stockInput);

    variantRow.append(td1, td2, td3);
    variantTableBody.append(variantRow);

    variantRowCount++;
})

addColorButton.addEventListener("click", (e) => {
    e.preventDefault();

    let colorRow = document.createElement("tr");
    let td1 = document.createElement("td");
    let td2 = document.createElement("td");

    let colorCodeInput = document.createElement("input");
    colorCodeInput.type = "text";
    colorCodeInput.id = `color_code_${colorRowCount}`;
    colorCodeInput.name = `colors[${colorRowCount}].code`;

    let colorNameInput = document.createElement("input");
    colorNameInput.type = "text";
    colorNameInput.id = `color_name_${colorRowCount}`;
    colorNameInput.name = `colors[${colorRowCount}].name`;

    td1.append(colorCodeInput);
    td2.append(colorNameInput);

    colorRow.append(td1, td2);

    colorTableBody.append(colorRow);
})

addSizeButton.addEventListener("click", (e) => {
    e.preventDefault();

    let block = document.createElement("div");
    let sizeInputLabel = document.createElement("label");
    let sizeInput = document.createElement("input");

    sizeInputLabel.innerHTML = `size_${sizeRowCount}: `;
    sizeInputLabel.htmlFor = `size_${sizeRowCount}`

    sizeInput.type = "text";
    sizeInput.id = `size_${sizeRowCount}`;
    sizeInput.name = `sizes[${sizeRowCount}]`;

    block.append(sizeInputLabel);
    block.append(sizeInput);

    sizeDiv.append(block);

    console.log(sizeRowCount);

    sizeRowCount++;
})