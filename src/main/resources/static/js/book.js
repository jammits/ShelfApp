
var csrfToken = document.querySelector('#token').value;

window.onload = function() {
    var bookId = document.querySelector('.book-id').value;

    fetch("/bookshelf/contains/" + bookId)
        .then(response => response.json())
        .then(data => {


            if (!data) {
                var button = document.getElementById("add-to-bookshelf");
                button.style.display = "block";  // show the button

                button.addEventListener('click', function() {




                    fetch("/bookshelf/add/" + bookId, {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json',
                            'X-CSRF-Token': csrfToken
                        },
                        body: JSON.stringify({bookId: bookId})
                    })

                        .then(response => {
                            if (response.ok) {
                                button.style.display = "none";  // hide the button
                                window.location.href = "/book/"+ bookId;
                            } else {
                                console.error('Error:', response.statusText);
                            }
                        })
                        .catch(error => console.error('Error:', error));


                })
            }else{
                var button = document.getElementById("add-to-bookshelf");
                button.style.display = "block";  // show the button
                button.textContent = "Delete from Bookshelf";

                button.addEventListener('click', function() {
                    fetch("/bookshelf/delete/" + bookId, {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json',
                            'X-CSRF-Token': csrfToken
                        },
                        body: JSON.stringify({bookId: bookId})
                    })
                        .then(response => {
                            if (response.ok) {
                                button.style.display = "none";  // hide the button
                                window.location.href = "/book/"+ bookId;
                            } else {
                                console.error('Error:', response.statusText);
                            }
                        })
                        .catch(error => console.error('Error:', error));
                })
            }
        })
        .catch(error => console.error('Error:', error));

    //Review delete button
    var deleteButton = document.querySelector('.delete-reviews-button');
    if (deleteButton) {
        deleteButton.addEventListener('click', function() {
            var bookId = document.querySelector('.book-id').value;
            var csrfToken = document.querySelector('#token').value;

            fetch("/deletereviews/" + bookId, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'X-CSRF-Token': csrfToken
                }
            })
                .then(response => {
                    if (response.ok) {
                        // If the request was successful, remove the reviews from the page.
                        var reviewList = document.querySelector('.book-reviews ul');
                        while (reviewList.firstChild) {
                            reviewList.removeChild(reviewList.firstChild);
                        }
                        // Also hide the delete button.
                        deleteButton.style.display = "none";
                    } else {
                        console.error('Error:', response.statusText);
                    }
                })
                .catch(error => console.error('Error:', error));
        });
    }
};




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

function friends() {
    fetch('/friends',{
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-Token': csrfToken
        },
    })
        .then(() => window.location.href = '/friends')
}