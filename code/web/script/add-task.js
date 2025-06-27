function startSelect(url) {
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

                var selects = document.getElementById("selects");
                var selectList = document.createElement("select");
                selectList.id = "select-plant";
                selectList.className = "win-select";
                selects.appendChild(selectList);

                for (var i=0; i<array.length; i++) {
                    var option = document.createElement("option");
                    option.value = array[i].id;
                    option.text = array[i].title + " - " + array[i].id;
                    selectList.appendChild(option);
                }

            }else {
                document.write("ответ сервера " + xhrequest.statusText);
            }
        }
    };
    xhrequest.send();
}

function addTask(url){
    document.getElementById('loading').innerHTML = "Loading Data...";

    var idTask = document.getElementById("select-task").value;
    var idPlant = document.getElementById("select-plant").value;
    var array = "" + idTask + "," + idPlant;

    var xhrequest = new XMLHttpRequest();
    xhrequest.open("post", url, true);
    xhrequest.responseType = 'text';
    xhrequest.onreadystatechange = function() {
        if (xhrequest.readyState == 4) {
            document.getElementById('loading').innerHTML = "";
            var status = xhrequest.status;
            if (status == 200) {
                var message = xhrequest.response;
                alert(message);
                document.getElementById("select-plant").remove();
                startSelect("/owner/selectPlant");
            }else {
                document.write("ответ сервера " + xhrequest.statusText);
            }
        }
    };
    xhrequest.send(array);
}