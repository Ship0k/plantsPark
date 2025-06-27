function createButton(report, id) {
    var element = document.createElement("input");
    element.setAttribute("type", "button");
    if (report == "не выполнено") {
        element.setAttribute("class", "delete");
        element.setAttribute("value", "удалить");
        element.setAttribute("onclick", "del('/owner/deleteTask', " + id + ")");
    }else {
        element.setAttribute("class", "perform");
        element.setAttribute("value", "подтвердить");
        element.setAttribute("onclick", "confir('/owner/confirm', " + id + ")");
    }
    return element;
}

function backfiling(id) {
    var row = document.getElementById(id);
    var taskReport = {"id":0,"plant":"","task":"","report":""};

    taskReport.id = row.cells[0].innerHTML;
    taskReport.plant = row.cells[1].innerHTML;
    taskReport.task = row.cells[2].innerHTML;
    taskReport.report = row.cells[3].innerHTML;

    return JSON.stringify(taskReport);
}

function tasks(url) {
    document.getElementById('loading').innerHTML = "Loading Data...";

    var xhrequest = new XMLHttpRequest();
    xhrequest.open("post", url, true);
    xhrequest.responseType = 'json';
    xhrequest.onreadystatechange = function() {
        if (xhrequest.readyState == 4) {
            document.getElementById('loading').innerHTML = "";
            var status = xhrequest.status;
            if (status == 200) {
                var array = xhrequest.response;

                var table = document.getElementById('table');
                var row;

                if (array == ""){
                    var message = document.createTextNode("вы еще не давали заданий");

                    row = table.insertRow(table.rows.length);
                    var cell = row.insertCell(0);
                    cell.setAttribute("colspan", "5");
                    cell.appendChild(message);
                }else {
                    for (var i = 0; i < array.length; i++) {
                        row = table.insertRow(table.rows.length);
                        row.setAttribute("id", "" + array[i].id);

                        var id = document.createTextNode(array[i].id);
                        var plant = document.createTextNode(array[i].plant);
                        var task = document.createTextNode(array[i].task);
                        var report = document.createTextNode(array[i].report);

                        var cell1 = row.insertCell(0);
                        var cell2 = row.insertCell(1);
                        var cell3 = row.insertCell(2);
                        var cell4 = row.insertCell(3);
                        var cell5 = row.insertCell(4);

                        var button = createButton(array[i].report, array[i].id);
                        cell1.appendChild(id);
                        cell2.appendChild(plant);
                        cell3.appendChild(task);
                        cell4.appendChild(report);
                        cell5.appendChild(button);
                    }
                }
            }else {
                document.write("ответ сервера " + xhrequest.statusText);
            }
        }
    };
    xhrequest.send();
}

function confir(url, id) {
    document.getElementById('loading').innerHTML = "Loading Data...";

    var xhrequest = new XMLHttpRequest();
    xhrequest.open("post", url, true);
    xhrequest.responseType = 'json';
    xhrequest.onreadystatechange = function() {
        if (xhrequest.readyState == 4) {
            document.getElementById('loading').innerHTML = "";
            var status = xhrequest.status;
            if (status == 200) {
                alert("Выполненое задание под Id=" + id + ", подтверждено в БД");
                document.getElementById(id).remove();
                if (document.getElementById("table").rows.length == 1) {
                    tasks("/owner/taskReport");
                }
            }else {
                document.write("ответ сервера " + xhrequest.statusText);
            }
        }
    };
    xhrequest.send(id);
}

function del(url, id) {
    if (confirm("Вы уверены что хотите удалить задание ID=" + id + " ?")) {
        document.getElementById('loading').innerHTML = "Loading Data...";

        var xhrequest = new XMLHttpRequest();
        xhrequest.open("post", url, true);
        xhrequest.responseType = 'text';
        xhrequest.onreadystatechange = function () {
            if (xhrequest.readyState == 4) {
                document.getElementById('loading').innerHTML = "";
                var status = xhrequest.status;
                if (status == 200) {
                    var message = xhrequest.response;
                    alert(message);
                    document.getElementById(id).remove();
                    if (document.getElementById("table").rows.length == 1) {
                        tasks("/owner/taskReport");
                    }
                } else {
                    document.write("ответ сервера " + xhrequest.statusText);
                }
            }
        };
        xhrequest.send(id);
    }else {
        return false;
    }
}