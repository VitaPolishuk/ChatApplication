var loginValue, passwordValue;
var login, password;

document.addEventListener('DOMContentLoaded', function () {
    login = document.getElementById("username");
    password = document.getElementById("password");

    login.addEventListener('input', function (event) {
        loginValue = event.currentTarget.value;
    });

    password.addEventListener('input', function (event) {
        passwordValue = event.currentTarget.value;
    });
});

function registration() {
    if (checkFields([loginValue, passwordValue])) {
        send('/registration', function (data) {
            document.location.href = '/chat';
            localStorage.setItem('user', JSON.stringify(data));
        });
    } else {
        $("#dialog").dialog();
    }
}

function authentication() {
    if (checkFields([loginValue, passwordValue])) {
        send('/authentication', function (data) {
            if (data !== "") {
                document.location.href = '/chat';
                localStorage.setItem('user', JSON.stringify(data));
            } else {
                login.value = "";
                password.value = "";
            }
        });
    } else {
        $("#dialog").dialog();
    }
}

function checkFields(arr) {
    return Array.isArray(arr) && arr.every(function (element) {
        return element !== "";
    });
}

function send(url, success, error) {
    $.ajax({
        type: "POST",
        url: url,
        data: JSON.stringify({
            login: loginValue,
            password: passwordValue
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        success: success,
        error: error
    });
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
