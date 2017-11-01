var stompClient = null;

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
            async: false,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            success: function (data) {
                if(data == 1){
                    alert("Регистрация прошла успешно!");
                window.open('chat', '_self', false);}
                else{
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
        }
        $.ajax({
            type: "POST",
            url: "/authentication",
            data: JSON.stringify(dataJson),
            async: false,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            dataType: 'json',
            success: function (data) {
                if(data != ""){
                    document.getElementById("active").innerHTML = data;
                    window.open('chat', '_self', false);}
                else{
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
//
// function setConnected(connected) {
//     $("#connect").prop("disabled", connected);
//     $("#disconnect").prop("disabled", !connected);
//     if (connected) {
//         $("#conversation").show();
//     }
//     else {
//         $("#conversation").hide();
//     }
//     $("#greetings").html("");
// }
//
// function connect() {
//     var socket = new SockJS('/websocket');
//     stompClient = Stomp.over(socket);
//     stompClient.connect({}, function (frame) {
//         setConnected(true);
//         console.log('Connected: ' + frame);
//         stompClient.subscribe('/greetings', function (greeting) {
//             showGreeting(JSON.parse(greeting.body).content);
//         });
//     });
// }
//
// function disconnect() {
//     if (stompClient !== null) {
//         stompClient.disconnect();
//     }
//     setConnected(false);
//     console.log("Disconnected");
// }
//
// function sendName() {
//     connect();
//     stompClient.send("/chatMessage", {}, JSON.stringify({'txtMessage': $("#txtMessage").val()}));
// }
//
// function showGreeting(message) {
//     $("#messages").append("<tr><td>" + message + "</td></tr>");
// }
//
// $(function () {
//     $("form").on('submit', function (e) {
//         e.preventDefault();
//     });
//     $( "#connect" ).click(function() { connect(); });
//     $( "#disconnect" ).click(function() { disconnect(); });
//     $( "#send" ).click(function() { sendName(); });
// });
