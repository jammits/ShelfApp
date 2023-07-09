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

window.onload = function() {
    var books = document.querySelectorAll('.single-book');
    books.forEach(function(book) {
        book.addEventListener('click', function() {
            var bookId = book.querySelector('.book-id').value;

            fetch("/book/" + bookId, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'X-CSRF-Token': csrfToken
                }
            })
                .then(() => window.location.href = "/book/" + bookId)
                .catch(error => console.error('Error:', error));
        });
    });
};
