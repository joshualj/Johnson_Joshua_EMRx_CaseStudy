let check = function() {
    if (document.getElementById('password').value ==
        document.getElementById('confirmPassword').value) {
        document.getElementById('message').style.color = 'green';
        document.getElementById('message').innerHTML = 'Password confirmed';
    } else {
        document.getElementById('message').style.color = 'red';
        document.getElementById('message').innerHTML = 'Password does not match';
    }
}