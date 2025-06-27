function createButton(report, id) {
    var element = document.createElement("input");
    element.setAttribute("type", "button");
    if (report == "не выполнено") {
        element.setAttribute("class", "perform");
        element.setAttribute("value", "выполнить");
        element.setAttribute("onclick", "perform('/forester/perform', " + id + ")");
    }else {
        element.setAttribute("class", "cancel");
        element.setAttribute("value", "отменить");
        element.setAttribute("onclick", "perform('/forester/cancel', " + id + ")");
    }
    return element;
}

function task(url) {
    document.getElementById('loading').innerHTML = "Loading Data...";

    var xhrequest = new XMLHttpRequest();
    xhrequest.responseType = 'json';
    function reqReadyStateChange(){
        if (xhrequest.readyState == 4) {
            document.getElementById('loading').innerHTML = "";
            var status = xhrequest.status;
            if (status == 200) {
                var array = xhrequest.response;

                var table = document.getElementById('table');
                var row;

                if (array == ""){
                    var message = document.createTextNode("задания отсутствуют");

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
    }
    xhrequest.open("post", url, true);
    xhrequest.onreadystatechange = reqReadyStateChange;
    xhrequest.send();
}

function perform(url, id) {
    document.getElementById('loading').innerHTML = "Loading Data...";

    var xhrequest = new XMLHttpRequest();
    xhrequest.open("post", url, true);
    xhrequest.responseType = 'json';
    xhrequest.onreadystatechange = function() {
        if (xhrequest.readyState == 4) {
            document.getElementById('loading').innerHTML = "";
            var status = xhrequest.status;
            if (status == 200) {
                var json = xhrequest.response;
                var row = document.getElementById(json.id);
                var button = createButton(json.report, json.id);

                row.cells[3].innerHTML = json.report;
                row.removeChild(row.cells[4]);
                row.insertCell(4).appendChild(button);
            }else {
                document.write("ответ сервера " + xhrequest.statusText);
            }
        }
    };
    xhrequest.send(id);
}