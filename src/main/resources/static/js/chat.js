document.addEventListener('DOMContentLoaded', function () {
    var authorize = document.getElementById('authorize');
    authorize.innerHTML += JSON.parse(localStorage.getItem('user')).login;
});