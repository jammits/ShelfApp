var csrfToken = document.querySelector('#token').value;


function overview() {
    fetch('/overview',{
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-Token': csrfToken
        },
    })
        .then(() => window.location.href = '/overview')
}

function bookshelf() {
    fetch('/bookshelf',{
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-Token': csrfToken
        },
    })
        .then(() => window.location.href = '/bookshelf')
}

function profile() {
    fetch('/profile',{
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-Token': csrfToken
        },
    })
        .then(() => window.location.href = '/profile')
}


document.querySelector(".button-add-friend").addEventListener("click", function(event) {
    event.preventDefault();
    var friendId = document.querySelector("#friendId").value;

    fetch('/friends/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-Token': csrfToken
        },
        body: friendId
    })
        .then(response => response.json())
        .then(data => {
            var messageElement = document.getElementById("message");
            if (data) {
                messageElement.textContent = "Friend was added successfully";

            } else {
                messageElement.textContent = "Failed to add friend";

            }
            messageElement.style.display = "block";
        })
        .then(()=>{

            fetch('/friends',{
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'X-CSRF-Token': csrfToken
                }
            })

            setTimeout(function() {
                window.location.href = '/friends';
            }, 1000);
        })
        .catch((error) => {
            console.error('Error:', error);
        });
});

document.querySelector(".button-delete-friend").addEventListener("click", function(event) {
    event.preventDefault();
    var friendId = document.querySelector("#friendId-delete").value;

    fetch('/friends/delete', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-Token': csrfToken
        },
        body: friendId
    })
        .then(response => response.json())
        .then(data => {
            var messageElement = document.getElementById("message");
            if (data) {
                messageElement.textContent = "Friend was deleted successfully";

            } else {
                messageElement.textContent = "Failed to delete friend";
            }
            messageElement.style.display = "block";
        })
        .then(()=>{

            fetch('/friends',{
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'X-CSRF-Token': csrfToken
                }
            })

            setTimeout(function() {
                window.location.href = '/friends';
            }, 1000);
        })
        .catch((error) => {
            console.error('Error:', error);
        });
});
