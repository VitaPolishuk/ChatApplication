var stompClient = null;
var login = "";

function registration() {

    var login = document.getElementById("username");
    var password = document.getElementById("password");

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
            url: "/registration",
            data: JSON.stringify(dataJson),
            dataType: 'json',
            async: false,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            success: function (data) {
                if (data != "") {
                    alert("Регистрация прошла успешно!");
                    document.getElementById("regAndAuthen").style.display = 'none';
                    document.getElementById("chat").style.display = 'block';
                    document.getElementById("name").innerHTML = data.login;
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

    var login = document.getElementById("username");
    var password = document.getElementById("password");

    if (login.value == "" || password.value == "") {
        alert("Поля не могут быть пустыми!")
    }
    else {
        var dataJson = {
            login: login.value,
            password: password.value
        };
        $.ajax({
            type: "POST",
            url: "/authentication",
            data: JSON.stringify(dataJson),
            dataType: 'json',
            async: false,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            success: function (data) {
                if (data != "") {
                    document.getElementById("regAndAuthen").style.display = 'none';
                    document.getElementById("chat").style.display = 'block';
                    document.getElementById("name").innerHTML = data.login;
                    login = data;
                }
                else {
                    alert("Неверный логин или пароль!");
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

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendMessage() {
    stompClient.send("/chatMessage", {}, JSON.stringify({'login': document.getElementById("name").innerHTML, 'message': $("#txtMessage").val()}));
}

function showGreeting(message) {
    $("#messages").append("<tr><td>" + "[ " + message.date + "]" + "</td></tr>");
    $("#messages").append("<tr><td>" + message.user.login +" : "+ message.message+"</td></tr>");

}

$(function () {
    connect();
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#connect").click(function () {
        connect()
    });
    $("#disconnect").click(function () {
        disconnect();
    });
    $("#send").click(function () {
        sendMessage();
    });
});