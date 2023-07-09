
var csrfToken = document.querySelector('#token').value


var clearSensitive = document.querySelector('.pass')

window.onload = function () {
    clearSensitive.innerHTML='';
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