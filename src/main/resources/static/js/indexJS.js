var stompClient = null;

function registration() {

    var login = document.getElementById("username");
    var password = document.getElementById("password");
    localStorage.setItem('userName', login.value);

    if (login.value == "" || password.value == "") {
        alert("Поля не могут быть пустыми!")
    }
    else {
        var dataJson = {
            login: login.value,
            password: password.value
        }
        $.ajax({
            type: "POST",
            url: "/token",
            data: JSON.stringify(dataJson),
            async: false,
            headers: {
                'Content-Type': 'application/json'
            },
            success: function (data) {
                if (data != "") {
                    alert("Регистрация прошла успешно!");
                    document.getElementById("regAndAuthen").style.display = 'none';
                    document.getElementById("chat").style.display = 'block';

                    var name = localStorage.getItem('userName');

                    document.getElementById("name").innerHTML = name;
                    localStorage.setItem('token'+name, data);
                    document.getElementById("txtMessage").value = "";
                }
                else {
                    alert("Такой логин уже существует");
                    document.getElementById("password").value = "";
                    document.getElementById("username").value = ""
                }
            },
            error: function () {
                alert("error");
            }

        })
    }
}

function authentication() {
       var nameAuth = document.getElementById("username").value;
        $.ajax({
            type: "POST",
            url: "/authentication",
            async: false,
            headers: {
                'Authorisation': 'Token '+localStorage.getItem('token'+nameAuth)
            },
            success: function (data) {
                if (data != "") {
                    document.getElementById("regAndAuthen").style.display = 'none';
                    document.getElementById("chat").style.display = 'block';
                    document.getElementById("name").innerHTML = nameAuth;
                }
            },
            error: function () {
                alert("Неверный логин или пароль!");
                document.getElementById("password").value = "";
                document.getElementById("username").value = ""
            }
        })
}

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/messages', function (message) {
            showGreeting(JSON.parse(message.body));
        });
    });
}

function sendMessage() {
    stompClient.send("/chatMessage", {}, JSON.stringify({'login': document.getElementById("name").innerHTML, 'message': $("#txtMessage").val()}));
    document.getElementById("txtMessage").value = "";
}

function showGreeting(message) {
    $("#messages").append("<tr><td>" + "[ " + message.date + "]" + "</td></tr>");
    $("#messages").append("<tr><td>" + message.user.login +" : "+ message.message+"</td></tr>");
}

$(function () {
    connect();
});