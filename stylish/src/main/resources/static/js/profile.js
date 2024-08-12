document.addEventListener('DOMContentLoaded', function () {
    const token = localStorage.getItem('jwt');

    if (token) {
        fetchUserData(token);
    } else {
        const currentUrl = window.location.href;
        window.location.href = `login.html?redirect=${encodeURIComponent(currentUrl)}`;
    }
})

function fetchUserData(token) {
    fetch('http://52.69.33.14/api/1.0/user/profile', {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Error fetching user data')
            }
            return response.json();
        })
        .then(json => {
            const data = json.data;
            console.log(data);
            if (data) {
                displayUserData(data);
            }
        })
        .catch(error => {
            console.error(error);
            localStorage.removeItem('jwt');
            const currentUrl = window.location.href;
            window.location.href = `login.html?redirect=${encodeURIComponent(currentUrl)}`;
        })
}

function displayUserData(data) {
    const profileContainer = document.createElement('div');
    profileContainer.id = 'userProfile';

    const usernameElement = document.createElement('p');
    usernameElement.innerHTML = `<strong>Username:</strong>${data.name}`;
    profileContainer.appendChild(usernameElement);

    const emailElement = document.createElement('p');
    emailElement.innerHTML = `<strong>Email:</strong>${data.email}`;
    profileContainer.appendChild(emailElement);

    document.querySelector('main').appendChild(profileContainer);
}