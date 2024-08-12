document.addEventListener('DOMContentLoaded', function () {
    const signupButton = document.getElementById('signupButton');

    signupButton.addEventListener('click', function (event) {
        event.preventDefault();

        const name = document.getElementById('name').value;
        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;

        fetch('http://52.69.33.14/api/1.0/user/signup', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                name: name,
                email: email,
                password: password
            })
        })
            .then(response => response.json())
            .then(json => {
                const data = json.data;
                if (data.access_token) {
                    localStorage.setItem('jwt', data.access_token);
                    console.log("JWT token stored in localStorage.");

                    const urlParams = new URLSearchParams(window.location.search);
                    const redirectUrl = urlParams.get('redirect');

                    window.location.href = '../profile.html';
                } else {
                    console.error("Token not found in response.");
                }
            })
            .catch(error => {
                console.error("Error", error);
            });
    });
});