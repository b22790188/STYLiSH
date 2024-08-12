let form = document.getElementById("campaign_form")

document.addEventListener('DOMContentLoaded', function () {
    const select = document.getElementById('productSelect');

    fetch('/api/1.0/products/all?getAll=true')
        .then(response => response.json())
        .then(result => {
            const data = result.data
            data.forEach(product => {
                const option = document.createElement('option');
                option.value = product.id;
                option.textContent = product.id + " - " + product.title;
                select.appendChild(option);
                console.log(product);
            })
        })
        .catch(err => console.log('Error:', err));
})

form.addEventListener("submit", (e) => {
    e.preventDefault();


    const formData = new FormData(form);

    const existingMessage = document.getElementById('message');
    if (existingMessage != null) {
        existingMessage.remove();
    }

    fetch('/admin/campaign', {
        method: 'POST',
        body: formData
    })
        .then(response => {
            if (!response.ok) {
                return response.json().then(result => {
                    throw new Error(result.error || 'Campaign already exist!');
                });
            }
            return response.json();
        })
        .then(result => {
            displayMessage(result.message);
        })
        .catch(err => {
            displayMessage(err.message);
        });

})

function displayMessage(message) {
    const messageContainer = document.createElement('div');
    messageContainer.id = 'message';
    messageContainer.textContent = message;

    form.parentNode.insertBefore(messageContainer, form);
}
