
var registerBtn = document.querySelector('.btn-register');
var fieldsAdded = false;
registerBtn.addEventListener('click', function() {

    if (!fieldsAdded) {
        // Create First Name input
        var firstNameInput = document.createElement('input');
        firstNameInput.type = 'text';
        firstNameInput.id = 'first-name';
        firstNameInput.name = 'first-name';
        firstNameInput.placeholder = 'Enter First Name';
        firstNameInput.style.display = 'block';

        // Create Last Name input
        var lastNameInput = document.createElement('input');
        lastNameInput.type = 'text';
        lastNameInput.id = 'last-name';
        lastNameInput.name = 'last-name';
        lastNameInput.placeholder = 'Enter Last Name';
        lastNameInput.style.display = 'block';

        // Get the input-buttons div
        var inputFieldsDiv = document.querySelector('.input-fields');

        // Append the inputs to the end of the input-fields div
        inputFieldsDiv.appendChild(firstNameInput);
        inputFieldsDiv.appendChild(lastNameInput);

        // Remove the Log In button
        var loginBtn = document.querySelector('.btn-submit');
        loginBtn.parentNode.removeChild(loginBtn);

        // Change Sign Up button to Register and its type to button
        this.textContent = 'Register';
        this.type = 'button';

        fieldsAdded = true; // Set the flag to true
    }


        // Change Sign Up button to Register and its type to submit
        this.textContent = 'Register';
        this.type = 'button';



        this.addEventListener('click', function() {
            var csrfToken = document.querySelector('#token').value;
            var sendUser = {
                'firstname': document.querySelector('#first-name').value,
                'lastname': document.querySelector('#last-name').value,
                'username': document.querySelector('#username').value,
                'password': document.querySelector('#password').value
            }
            var errorMsg = document.querySelector('#error-message');
            if (!sendUser.firstname || !sendUser.lastname || !sendUser.username || !sendUser.password) {

                errorMsg.classList.add('error-box'); // Add the box and shadow
                errorMsg.style.color = 'red'; // Make the text red
                errorMsg.textContent = 'All fields must be filled out';
                return;
            }else {
                errorMsg.classList.remove('error-box'); // Remove the box and shadow
                errorMsg.style.color = 'inherit'; // Revert the text color to the default
                errorMsg.textContent = ''; // Clear the error message
            }
            fetch('/register', {
                method : 'POST',
                headers : {
                    'Content-Type': 'application/json',
                    'X-CSRF-Token': csrfToken
                },
                body: JSON.stringify(sendUser)
            })
                .then(response => response.json())
                .then(data => {
                    if (!data) {
                        errorMsg.classList.add('error-box'); // Add the box and shadow
                        errorMsg.style.color = 'red'; // Make the text red
                        errorMsg.textContent = 'Username is taken';
                    } else {
                        window.location.href='/login';
                    }
                })

        });
    });

