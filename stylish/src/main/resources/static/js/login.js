const nativeLoginButton = document.getElementById('nativeLogin');

<!-- Add the Facebook SDK for Javascript -->
document.addEventListener('DOMContentLoaded', function () {
    (function (d, s, id) {
        var js,
            fjs = d.getElementsByTagName(s)[0];
        if (d.getElementById(id)) {
            return;
        }
        js = d.createElement(s);
        js.id = id;
        js.src = "https://connect.facebook.net/en_US/sdk.js";
        fjs.parentNode.insertBefore(js, fjs);
    })(document, "script", "facebook-jssdk");

// Initialize the SDK and handle login
    window.fbAsyncInit = function () {
        FB.init({
            appId: "1605787739991700",
            xfbml: true,
            version: "v20.0",
        });
    };
})

nativeLoginButton.addEventListener('click', function (event) {
    event.preventDefault();

    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;


    fetch('http://52.69.33.14/api/1.0/user/signin', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            provider: "native",
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

                window.location.href = redirectUrl ? redirectUrl : 'index.html';

            } else {
                console.error("Token not found in response.");
            }
        })
        .catch(error => {
            console.error("Error", error);
        });
});


// Check login state and fetch user information
function checkLoginState() {
    FB.getLoginStatus(function (response) {
        if (response.status === 'connected') {
            fetchUserProfile();
        } else {
            FB.login(function (response) {
                if (response.authResponse) {
                    //get access token
                    let accessToken = response.authResponse.accessToken;
                    sendAccessToken(accessToken);
                } else {
                    console.log("User cancelled login or did not fully authorize.");
                }
            }, {scope: 'public_profile,email'});
        }
    });
}

function fetchUserProfile() {
    FB.api("/me", {fields: "name,email,picture"}, function (response) {
        document.getElementById("profile").innerHTML =
            "Good to see you, " + response.name + ". I see your email address is " + response.email
            + "Your Image url is " + response.picture.data.url;
    });
}

function sendAccessToken(accessToken) {
    fetch('/api/1.0/user/signin', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            provider: "facebook",
            access_token: accessToken
        })
    })
        .then(response => response.json())
        .then(data => {
            console.log("Response", data)
        })
        .catch(error => {
            console.error("Error", error);
        })
}


