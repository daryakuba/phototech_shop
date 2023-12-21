async function customer_enter() {
    var username = document.getElementById("username_cust").value;
    var password = document.getElementById("password_cust").value;


    localStorage.setItem('username',username);

    let response = await fetch('http://localhost:8080/user/' + username);
    let text = await response.text();

    if (text != 'OK') {
        alert("Такого юзера не существует");
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
        let response4 = await fetch('http://localhost:8080/users/getIdByLogin/' + username);
        text = await response4.text();
        const user_id = parseInt(text);

        localStorage.setItem('userNowId', user_id);

        const url = '../lk_customer/customer.html'; // URL-адрес HTML-страницы
        window.open(url);
    } else {
        alert("Пароль неверный");
    }
}