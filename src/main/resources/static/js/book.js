window.onload = function() {
    var bookId = document.querySelector('.book-id').value;

    fetch("/bookshelf/contains/" + bookId)
        .then(response => response.json())
        .then(data => {
            var csrfToken = document.querySelector('#token').value;

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
};