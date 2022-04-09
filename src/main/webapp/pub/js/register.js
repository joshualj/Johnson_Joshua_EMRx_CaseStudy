//set email and password RegEx
// const emailRegEx = /^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/
// const passwordRegEx = /^(?=.*\d)(?=.*[A-Za-z])([^\s]){8,20}$/
//
// $('#registerForm').on("submit", function(event){
//     //prevent default submit
//     event.preventDefault();
//
//     //Alert user of specific invalid credentials
//     if(emailRegEx.test($('#email').val()) && passwordRegEx.test($('#password').val()) && ($('#password').val() == $('#confirmPassword').val())) {
//         alert("Success");
//     } else if(!(emailRegEx.test($('#email').val()))) {
//         alert("Email invalid");
//     } else if(!(passwordRegEx.test($('#password').val()))) {
//         alert("Password invalid");
//     } else {
//         alert("Passwords do not match.");
//     }
// });

// Example starter JavaScript for disabling form submissions if there are invalid fields
(function () {
    'use strict'

    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    var forms = document.querySelectorAll('.needs-validation')

    // Loop over them and prevent submission
    Array.prototype.slice.call(forms)
        .forEach(function (form) {
            form.addEventListener('submit', function (event) {
                if (!form.checkValidity()) {
                    event.preventDefault()
                    event.stopPropagation()
                }

                form.classList.add('was-validated')
            }, false)
        })
})();