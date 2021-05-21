function rangeValueAge(){
    var rng=document.getElementById('age'); //rng - это Input
    var p=document.getElementById('one'); // p - абзац
    p.innerHTML="Возраст " + rng.value;
}


/*<table class="table-questionnaries">
            <tr>
              Имя
              Пол
              <td class="about-me"><label class="field-name">О себе</label></td>
              <td><button class="btn-like">Лайк</button></td>
            </tr>*/


function loadQuestionnaries() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var users = JSON.parse(this.responseText);
            var html = '';
            for (var i = 0; i < users.length; i++) {
                var user = users[i];
                console.log(user);
                html = html + '<td><label class="field-name">' + user.name + '</label></td>\n' +
                    '<td><label class="field-name">' + user.age + '</label></td>\n' +
                    '<td><label class="field-name">' + user.gender + '</label></td>\n' +
                    '<td class="about-me"><label class="field-name">' + user.info + '</label></td>' +
                    '<td><button class="btn-like" onclick="likeUser('+ user.login +')>Лайк</button></td></tr>';
            }
            document.getElementById("usersQuestionnaries").innerHTML = html;
        }
    };
    
    function likeUser(login) {
        var xhttp = new XMLHttpRequest();
        //xhttp.open("GET", "http://localhost:8080/users/delete/" + login, true);
        xhttp.send();
    }
    xhttp.open("GET", "http://localhost:8080/users/findAll", true);
    xhttp.send();
}