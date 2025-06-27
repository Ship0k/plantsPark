function filling(json) {
    document.getElementById('id').innerHTML = json.id;
    document.getElementById('title').innerHTML = json.title;
    if (json.plantDetails.landingData == null) {
        document.getElementById('landing').innerHTML = "не посажено";
    }else {
        document.getElementById('landing').innerHTML = json.plantDetails.landingData;
    }
    document.getElementById('art').innerHTML = json.plantDetails.artWorkN;
    document.getElementById('treatment').innerHTML = json.plantDetails.treatmentN;
    if (json.plantDetails.destructionDate == null) {
        document.getElementById('destruction').innerHTML = "-";
    }else {
        document.getElementById('destruction').innerHTML = json.plantDetails.destructionDate;
    }
}

function backfiling() {
    var plant = {id:0,title:"",plantDetails:{landingData:0,treatmentN:0,artWorkN:0,destructionDate:0,id:0}};
    plant.id = Number(document.getElementById('id').innerHTML);
    plant.title = document.getElementById('title').innerHTML;
    if (document.getElementById('landing').innerHTML == "не посажено") {
        delete plant.plantDetails.landingData;
    }else {
        plant.plantDetails.landingData = document.getElementById('landing').innerHTML;
    }
    plant.plantDetails.artWorkN = Number(document.getElementById('art').innerHTML);
    plant.plantDetails.treatmentN = Number(document.getElementById('treatment').innerHTML);
    if (document.getElementById('destruction').innerHTML == "-") {
        delete plant.plantDetails.destructionDate;
    }else {
        plant.plantDetails.destructionDate = document.getElementById('destruction').innerHTML;
    }
    return JSON.stringify(plant);
}

function start(url) {
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
                filling(json);
            }else {
                document.write("ответ сервера " + xhrequest.statusText);
            }
        }
    };
    xhrequest.send();
}

function view(url) {
    var plantJSON = backfiling();
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
                if (json.id == 0){
                    alert("Конец списка, смените направление!")
                }else {
                    filling(json);
                }
            }else {
                document.write("ответ сервера " + xhrequest.statusText);
            }
        }
    };
    xhrequest.send(plantJSON);
}

function search(url) {
    document.getElementById('loading').innerHTML = "Loading Data...";
    var check = document.getElementById("input").value;

    var xhrequest = new XMLHttpRequest();
    xhrequest.open("post", url, true);
    xhrequest.responseType = 'json';
    xhrequest.onreadystatechange = function () {
        if (xhrequest.readyState == 4) {
            document.getElementById('loading').innerHTML = "";
            var status = xhrequest.status;
            if (status == 200) {
                var json = xhrequest.response;
                if (json.id == 0) {
                    alert("Растения с id = " + check + ", не существует в базе данных")
                }else {
                    filling(json);
                }
                document.getElementById("input").value = "";
            }else {
                document.write("ответ сервера " + xhrequest.statusText);
            }
        }
    };
    xhrequest.send(check);
}