function createButtonEdit(id) {
    var element = document.createElement("input");
    element.setAttribute("type", "button");
    element.setAttribute("class", "edit");
    element.setAttribute("value", "редактировать");
    element.setAttribute("onclick", "edit(" + id + ")");
    return element;
}

function createButtonDelete(id) {
    var element = document.createElement("input");
    element.setAttribute("type", "button");
    element.setAttribute("class", "delete");
    element.setAttribute("value", "X");
    element.setAttribute("title", "удалить");
    element.setAttribute("onclick", "del('/owner/deletePlant', " + id + ")");
    return element;
}

function createButtonCancel(id) {
    var element = document.createElement("input");
    element.setAttribute("type", "button");
    element.setAttribute("class", "cancel");
    element.setAttribute("value", "отменить");
    element.setAttribute("onclick", "cancel('/owner/returnPlant', " + id + ")");
    return element;
}

function createButtonSave(id) {
    var element = document.createElement("input");
    element.setAttribute("type", "button");
    element.setAttribute("class", "perform");
    element.setAttribute("value", "сохранить");
    element.setAttribute("onclick", "save('/owner/updatePlant', " + id + ")");
    return element;
}

function fillingRow(json, id) {
    var row = document.getElementById(id);

    row.cells[1].innerHTML = json.title;

    if (json.plantDetails.landingData == null) {
        row.cells[2].innerHTML = "не посажено";
    }else {
        row.cells[2].innerHTML = json.plantDetails.landingData;
    }

    row.cells[3].innerHTML = json.plantDetails.artWorkN;
    row.cells[4].innerHTML = json.plantDetails.treatmentN;

    if (json.plantDetails.destructionDate == null) {
        row.cells[5].innerHTML = "-";
    }else {
        row.cells[5].innerHTML = json.plantDetails.destructionDate;
    }

    var buttonEdit = createButtonEdit(json.id);
    var buttonDelete = createButtonDelete(json.id);

    row.removeChild(row.cells[6]);
    row.insertCell(6).appendChild(buttonEdit);
    row.cells[6].appendChild(buttonDelete);
}

function show(state) {
    document.getElementById("modalForm").style.display = state;
    document.getElementById("filter").style.display = state;
}

function dateInput(dataInner) {
    var date = dataInner;
    var formDate = date.charAt(6);
    formDate += date.charAt(7);
    formDate += date.charAt(8);
    formDate += date.charAt(9);
    formDate += "-";
    formDate += date.charAt(3);
    formDate += date.charAt(4);
    formDate += "-";
    formDate += date.charAt(0);
    formDate += date.charAt(1);
    return  formDate;
}

function dateOut(dataInner) {
    var date = dataInner;
    var formDate = date.charAt(8);
    formDate += date.charAt(9);
    formDate += " ";
    formDate += date.charAt(5);
    formDate += date.charAt(6);
    formDate += " ";
    formDate += date.charAt(0);
    formDate += date.charAt(1);
    formDate += date.charAt(2);
    formDate += date.charAt(3);
    return  formDate;
}

function jsonFiling(id){
    var plant = {id:0,title:"",plantDetails:{landingData:0,treatmentN:0,artWorkN:0,destructionDate:0,id:0}};
    plant.id = id;
    plant.title = document.getElementById('title' + id).value;
    if (!document.getElementById('landing' + id).value) {
        delete plant.plantDetails.landingData;
    }else {
        plant.plantDetails.landingData = dateOut(document.getElementById('landing' + id).value);
    }
    plant.plantDetails.artWorkN = Number(document.getElementById('art' + id).value);
    plant.plantDetails.treatmentN = Number(document.getElementById('treatment' + id).value);
    if (!document.getElementById('destruction' + id).value) {
        delete plant.plantDetails.destructionDate;
    }else {
        plant.plantDetails.destructionDate = dateOut(document.getElementById('destruction' + id).value);
    }
    return JSON.stringify(plant);
}

function plants(url) {
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

                if (array == "") {
                    var message = document.createTextNode("в парке нет ниодного растения");

                    row = table.insertRow(table.rows.length);
                    var cell = row.insertCell(0);
                    cell.setAttribute("colspan", "7");
                    cell.appendChild(message);
                }else {
                    for (var i = 0; i < array.length; i++) {
                        row = table.insertRow(table.rows.length);
                        row.setAttribute("id", "" + array[i].id);

                        var id = document.createTextNode(array[i].id);
                        var title = document.createTextNode(array[i].title);

                        var landingData;
                        if (array[i].plantDetails.landingData == null) {
                            landingData = document.createTextNode("не посажено");
                        }else {
                            landingData = document.createTextNode(array[i].plantDetails.landingData);
                        }

                        var artWorkN = document.createTextNode(array[i].plantDetails.artWorkN);
                        var treatmentN = document.createTextNode(array[i].plantDetails.treatmentN);

                        var destructionDate;
                        if (array[i].plantDetails.destructionDate == null) {
                            destructionDate = document.createTextNode("-");
                        }else {
                            destructionDate = document.createTextNode(array[i].plantDetails.destructionDate);
                        }

                        var cell1 = row.insertCell(0);
                        var cell2 = row.insertCell(1);
                        var cell3 = row.insertCell(2);
                        var cell4 = row.insertCell(3);
                        var cell5 = row.insertCell(4);
                        var cell6 = row.insertCell(5);
                        var cell7 = row.insertCell(6);

                        var buttonEdit = createButtonEdit(array[i].id);
                        var buttonDelete = createButtonDelete(array[i].id);

                        cell1.appendChild(id);
                        cell2.appendChild(title);
                        cell3.appendChild(landingData);
                        cell4.appendChild(artWorkN);
                        cell5.appendChild(treatmentN);
                        cell6.appendChild(destructionDate);
                        cell7.appendChild(buttonEdit);
                        cell7.appendChild(buttonDelete);
                    }
                }
            }else {
                document.write("ответ сервера " + xhrequest.statusText);
            }
        }
    };
    xhrequest.send();
}

function edit(id) {
    var row = document.getElementById(id);

    var title = document.createElement("input");
    title.id = "title" + id;
    title.type = "text";
    title.pattern = "^[А-Яа-яЁё]+$";
    title.required;
    title.value = row.cells[1].innerHTML;

    var landing = document.createElement("input");
    landing.id = "landing" + id;
    landing.type = "date";
    if (row.cells[2].innerHTML != "не посажено") {
        landing.value = dateInput(row.cells[2].innerHTML);
    }

    var art = document.createElement("input");
    art.id = "art" + id;
    art.type = "number";
    art.value = row.cells[3].innerHTML;

    var treatment = document.createElement("input");
    treatment.id = "treatment" + id;
    treatment.type = "number";
    treatment.value = row.cells[4].innerHTML;

    var destruction = document.createElement("input");
    destruction.id = "destruction" + id;
    destruction.type = "date";
    if (row.cells[5].innerHTML != "-") {
        destruction.value = dateInput(row.cells[5].innerHTML);
    }

    row.removeChild(row.cells[1]);
    row.insertCell(1).appendChild(title);
    row.removeChild(row.cells[2]);
    row.insertCell(2).appendChild(landing);
    row.removeChild(row.cells[3]);
    row.insertCell(3).appendChild(art);
    row.removeChild(row.cells[4]);
    row.insertCell(4).appendChild(treatment);
    row.removeChild(row.cells[5]);
    row.insertCell(5).appendChild(destruction);

    var save = createButtonSave(id);
    var cancel = createButtonCancel(id);
    row.removeChild(row.cells[6]);
    row.insertCell(6).appendChild(save);
    row.cells[6].appendChild(cancel);
}

function showError(container, errorMessage) {
    container.className = 'error';
    var msgElem = document.createElement('span');
    msgElem.className = "error-message";
    msgElem.innerHTML = errorMessage;
    container.appendChild(msgElem);
}
function resetError(container) {
    container.className = 'valid';
    if (container.lastChild.className == "error-message") {
        container.removeChild(container.lastChild);
    }
}
function valid(id) {

}
function save(url, id) {
    var title = document.getElementById("title" + id);
    var landing = document.getElementById("landing" + id);
    var art = document.getElementById("art" + id);
    var treatment = document.getElementById("treatment" + id);
    var destruction = document.getElementById("destruction" + id);
    var validate = /^[А-Яа-яЁё]+$/;

    resetError(title.parentNode);
    if (!title.value) {
        showError(title.parentNode, ' обязательное поле');
    } else if (!validate.test(title.value)) {
        showError(title.parentNode, ' только символы кириллицы');
    }

    resetError(landing.parentNode);
    if (!landing.value && destruction.value) {
        showError(landing.parentNode, ' обязательное поле, если указана дата уничтожения');
    }

    resetError(art.parentNode);
    if (art.value < 0) {
        showError(art.parentNode, ' только положительные числа');
    } else if (art.value > 0 && !landing.value) {
        showError(art.parentNode, ' укажите дату посадки или оставьте поле пустым');
    }

    resetError(treatment.parentNode);
    if (treatment.value < 0) {
        showError(treatment.parentNode, ' только положительные числа');
    } else if (treatment.value > 0 && !landing.value) {
        showError(treatment.parentNode, ' укажите дату посадки или оставьте поле пустым');
    }

    resetError(destruction.parentNode);
    if (landing.value > destruction.value && destruction.value) {
        showError(destruction.parentNode, ' не может быть раньше, чем дата посадки');
    }

    var row = document.getElementById(id);
    var elements = row.getElementsByTagName("td");
    var check = true;

    for (var i = 0; i < elements.length; i++) {
        if (elements[i].classList.contains("error")) {
            check = false;
            i = elements.length;
        }
    }

    if (check) {
        if (confirm("Сохранить действия с растением под номером ID=" + id + " ?")) {
            var gson = jsonFiling(id);
            document.getElementById('loading').innerHTML = "Loading Data...";

            var xhrequest = new XMLHttpRequest();
            xhrequest.open("post", url, true);
            xhrequest.responseType = 'json';
            xhrequest.onreadystatechange = function () {
                if (xhrequest.readyState == 4) {
                    document.getElementById('loading').innerHTML = "";
                    var status = xhrequest.status;
                    if (status == 200) {
                        var json = xhrequest.response;
                        fillingRow(json, id);
                    } else {
                        document.write("ответ сервера " + xhrequest.statusText);
                    }
                }
            };
            xhrequest.send(gson);
        } else {
            return false;
        }
    }
}

function cancel(url, id) {
    if (confirm("Отменить действия с растением ID=" + id + " ?")) {
        document.getElementById('loading').innerHTML = "Loading Data...";

        var xhrequest = new XMLHttpRequest();
        xhrequest.open("post", url, true);
        xhrequest.responseType = 'json';
        xhrequest.onreadystatechange = function () {
            if (xhrequest.readyState == 4) {
                document.getElementById('loading').innerHTML = "";
                var status = xhrequest.status;
                if (status == 200) {
                    var json = xhrequest.response;
                    fillingRow(json, id);
                    //correction
                    for (var i = 1; i < 6; i++) {
                        document.getElementById(id).cells[i].className = 'valid';
                    }
                    //
                }else {
                    document.write("ответ сервера " + xhrequest.statusText);
                }
            }
        };
        xhrequest.send(id);
    }else {
        return false;
    }
}

function del(url, id) {
    if (confirm("Вы уверены что хотите удалить растение с номером ID=" + id + " ?")) {
        document.getElementById('loading').innerHTML = "Loading Data...";
        var idPlant = document.getElementById(id).cells[0].innerHTML;

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
                        plants("/owner/plants");
                    }
                } else {
                    document.write("ответ сервера " + xhrequest.statusText);
                }
            }
        };
        xhrequest.send(idPlant);
    }else {
        return false;
    }
}