
var csrfToken = document.querySelector('#token').value

var searchBtn = document.querySelector('.search-button')

searchBtn.addEventListener('click', function () {

    var searchTerm = document.querySelector('.search-term').value


    fetch('/googlebook', {
        method : 'POST',
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-Token': csrfToken
        },
        body: searchTerm
    })
        .then(response => response.json())
        .then(data =>{
            var searchResults = document.querySelector('.search-results')

            // Clear previous search results
            searchResults.innerHTML = '';

            if(data.items == null){
                return;
            }

            // Create a Set to store unique title, subtitle and author combinations
            let titleSubtitleAuthorSet = new Set();

            // Filter out duplicates based on title, subtitle, and author
            data.items = data.items.filter((item) => {
                // Form a composite key
                let key = item.volumeInfo.title + '|' + item.volumeInfo.subtitle;

                if (item.volumeInfo.authors) {
                    key += '|' + item.volumeInfo.authors.join(',');
                }

                if (!titleSubtitleAuthorSet.has(key)) {
                    titleSubtitleAuthorSet.add(key);
                    return true;
                }
                return false;
            });

            data.items.forEach((book) => {
                var bookContainer = document.createElement('div');
                bookContainer.classList.add('book-info');
                bookContainer.style.border = '1px solid black';
                bookContainer.style.background = 'seashell';
                bookContainer.style.display = 'inline-block';
                bookContainer.style.width = '500px';
                bookContainer.style.overflow = 'auto';
                bookContainer.style.maxHeight = '500px';
                searchResults.appendChild(bookContainer);

                var bookDetails = {
                    title: book.volumeInfo.title,
                    subtitle: book.volumeInfo.subtitle,
                    author: book.volumeInfo.authors,
                    description: book.volumeInfo.description,
                    publisher: book.volumeInfo.publisher,
                    imageLink: null
                };

                //Book Title
                var bookTitle = document.createElement('h3')
                bookTitle.classList.add('book-title');
                bookTitle.textContent = book.volumeInfo.title;
                bookContainer.appendChild(bookTitle);

                //Book Subtitle
                if (book.volumeInfo.subtitle) {
                    var bookSubTitle = document.createElement('h4');
                    bookSubTitle.classList.add('book-subtitle');
                    bookSubTitle.textContent = book.volumeInfo.subtitle;
                    bookContainer.appendChild(bookSubTitle);
                }

                //Book Author(s)
                var bookAuthor = document.createElement('h5')
                bookAuthor.classList.add('book-author');
                if (book.volumeInfo.authors && book.volumeInfo.authors.length > 0) {
                    bookAuthor.textContent = book.volumeInfo.authors.join(', ');
                } else {
                    bookAuthor.textContent = 'No authors available';
                }
                bookContainer.appendChild(bookAuthor);

                // Book Image
                if (book.volumeInfo.imageLinks) {
                    const imagePriorities = ["thumbnail", "smallThumbnail", "small", "medium", "large", "extraLarge"];
                    let imageSrc = null;
                    for (let i = imagePriorities.length - 1; i >= 0; i--) {
                        const priority = imagePriorities[i];
                        if (book.volumeInfo.imageLinks[priority]) {
                            imageSrc = book.volumeInfo.imageLinks[priority];
                            bookDetails.imageLink = book.volumeInfo.imageLinks[priority];
                            break;
                        }
                    }
                    if (imageSrc) {
                        var bookImage = document.createElement('img');
                        bookImage.classList.add('book-image');
                        bookImage.src = imageSrc;
                        bookContainer.appendChild(bookImage);
                    } else {
                        var noImageText = document.createElement('p');
                        noImageText.textContent = 'No image available';
                        bookContainer.appendChild(noImageText);
                    }
                }  else {
                    var noImageText = document.createElement('p');
                    noImageText.textContent = 'No image available';
                    bookContainer.appendChild(noImageText);
                }

                //Book Description
                var bookDescription = document.createElement('p');
                bookDescription.classList.add('book-desc');
                if (book.volumeInfo.description) {
                    bookDescription.textContent = book.volumeInfo.description;
                } else {
                    bookDescription.textContent = 'No description available';
                }
                bookContainer.appendChild(bookDescription);

                //Making each book container clickable
                bookContainer.addEventListener('click', function() {

                    // Send a POST request to the backend
                    fetch('/book', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json',
                            'X-CSRF-Token': csrfToken
                        },
                        body: JSON.stringify(bookDetails)
                    })
                        .then(response => response.text())
                        .then(url => window.location.href = url)
                        .catch(error => {
                            console.log(error);
                        });
                });

            })

        })
})


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