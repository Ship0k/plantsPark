function getRole() {
    var role = document.getElementById("role");
    var roleButton = document.getElementById("role__button");

    var xhrequest = new XMLHttpRequest();
    xhrequest.open("post", "/home", true);
    xhrequest.responseType = 'text';
    xhrequest.onreadystatechange = function() {
        if (xhrequest.readyState == 4) {
            var status = xhrequest.status;
            if (status == 200) {
                var message = xhrequest.response;
                role.innerHTML = message;
                if (message == "Forester") {
                    document.getElementsByClassName("guest")[0].style.display = 'none';
                    document.getElementsByClassName("forester")[0].style.display='block';
                }else if (message == "Owner") {
                    document.getElementsByClassName("guest")[0].style.display='none';
                    document.getElementsByClassName("owner")[0].style.display='block';
                    document.getElementsByClassName("owner")[1].style.display='block';
                }

                if (message != "Guest") {
                    roleButton.className = "cancel";
                    roleButton.value = "Выход";
                    roleButton.onclick = logout;
                }
            }else {
                document.write("ответ сервера " + xhrequest.statusText);
            }
        }
    };
    xhrequest.send();
}

function login() {
    document.location.href = "/login";
}

function logout() {
    if (confirm("Вы уверены что хотите выйти?")) {
        var xhrequest = new XMLHttpRequest();
        xhrequest.open("post", "/logout");
        xhrequest.onreadystatechange = function () {
            if (xhrequest.readyState == 4) {
                var status = xhrequest.status;
                if (status == 200) {
                    document.location.href = "/home";
                } else {
                    document.write("ответ сервера " + xhrequest.statusText);
                }
            }
        };
        xhrequest.send();
    }else {
        return false;
    }
}