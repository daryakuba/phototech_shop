async function admin_enter() {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

    let response = await fetch('http://localhost:8080/user/' + username);
    let text = await response.text();

    if (text != 'OK') {
        alert("Такого юзера не существует");
        return;
    }

    let response2 = await fetch('http://localhost:8080/user/admin/check/' + username);
    text = await response2.text();

    if (text != 'ADMIN') {
        alert("Ты не админ");
        return;
    }

    let response3 = await fetch('http://localhost:8080/user/password/' + username, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            password: password
        })
    })
    text = await response3.text();
    if (text == "OK") {
        const url = '../lk_admin/admin.html'; // URL-адрес HTML-страницы
        window.open(url);
    } else {
        alert("Пароль неверный");
    }
}




