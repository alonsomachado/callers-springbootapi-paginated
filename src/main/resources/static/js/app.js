const urlheroku = "https://turismopwa.herokuapp.com";

const urllocalhost = "http://localhost:8080";
//const urllocalhost = "https://turismopwa.herokuapp.com"; // Somente funciona com HTTPS

function showList(message) {
    $("#listaCalls").append("<div class='card'> <h5 class='card-header'> Titulo  </h5> <div class='card-body'> <h5 class='card-title'> Special title treatment </h5><p class='card-text'> TEXTO </p> <a href='#' class='btn btn-primary'>BOTAO</a> </div> </div>");
 }


function getCalls(){

    var url  = urllocalhost+"/api/all";
    var xhr  = new XMLHttpRequest();
    xhr.open('GET', url, true);

    xhr.onload = function () {

        var notes = xhr.responseText;
        console.log(notes);
        probitem = JSON.parse(JSON.stringify(xhr.responseText));
        console.log(probitem);

        if (xhr.readyState == 4 && xhr.status == "200") {
            document.getElementById('listaCalls').innerHTML = "";
            if (notes == null || notes.length==0){
                document.getElementById('listaCalls').innerHTML = "Não Existem Calls Cadastradas.."
            }

            showList(notes);

            $("#resposta").append("<span>"+notes+"</span>");
        } else {
            alert('error in request');
        }
    }
    xhr.send(null);
}

function deleteCall() {

	var data = {};
	data.id = document.getElementById("callid").value;
	var id = data.id;
	var url = urllocalhost+"/api/delete/"+id;

	var jsondata = JSON.stringify(data);

	var xhr = new XMLHttpRequest();
	xhr.open("DELETE", url, true);
	xhr.setRequestHeader('Content-type','application/json; charset=utf-8');
	xhr.onload = function () {
		if (xhr.readyState == 4 && xhr.status == "200") {
			console.log("Deleted: "+xhr.responseText);
		} else {
			//
		}
	}
	xhr.send(jsondata);
}

function showStats() {

    var url = urllocalhost+"/statsapi/";
	var xhr  = new XMLHttpRequest();
        xhr.open('GET', url, true);

        xhr.onload = function () {

            var notes = xhr.responseText;
            console.log(notes);
            probitem = JSON.parse(JSON.stringify(xhr.responseText));
            console.log(probitem);

            if (xhr.readyState == 4 && xhr.status == "200") {
                document.getElementById('listaStats').innerHTML = "";
                if (notes == null || notes.length==0){
                    document.getElementById('listaStats').innerHTML = "Não Existem Estatisticas Registradas/Registadas.."
                }
                $("#resposta").empty();
                $("#resposta").append("<span>"+notes+"</span>");
            } else {
                alert('error in request');
            }
        }
        xhr.send(null);
}

function showStatsController() {

    var url = urllocalhost+"/statsapi/controllerstatistics/all";
	var xhr  = new XMLHttpRequest();
        xhr.open('GET', url, true);

        xhr.onload = function () {

            var notes = xhr.responseText;
            console.log(notes);
            probitem = JSON.parse(JSON.stringify(xhr.responseText));
            console.log(probitem);

            if (xhr.readyState == 4 && xhr.status == "200") {
                document.getElementById('listaStats').innerHTML = "";
                if (notes == null || notes.length==0){
                    document.getElementById('listaStats').innerHTML = "Não Existem Estatisticas Registradas/Registadas.."
                }
                $("#respcontroller").empty();
                $("#respcontroller").append("<span>"+notes+"</span>");
            } else {
                alert('error in request');
            }
        }
        xhr.send(null);
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
});