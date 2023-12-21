async function registration() {
        var username = document.getElementById("username_new").value;
        var password = document.getElementById("password_new").value;
    var name = document.getElementById("name").value;
    var phone_number = document.getElementById("phone_number").value;
    var status = "Customer"

    const response = await fetch('http://localhost:8080/user/' + username);
    const text = await response.text();

    if (text == 'OK') {
        
        alert("Такой юзер уже существует");
        return;
    }

    const response5 = await fetch('http://localhost:8080/usersEco', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            password: password,
            login: username,
            name: name,
            phone_number: phone_number,
            status: status
        })
    })
    

    if (response5.status === 200) {

        const response4 = await fetch('http://localhost:8080/users/getIdByLogin/' + username);
        const text4 = await response4.text();

        const u_id = parseInt(text4);

        localStorage.setItem('userNowId', u_id);

       const id = localStorage.getItem('userNowId');

        const response5 = await fetch('http://localhost:8080/cart', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            user: {
              id: id
            },
            products: []
          })
    })
        const url = '../lk_customer/customer.html'; // URL-адрес HTML-страницы
        window.open(url);
        alert("Вы успешно зарегистрированы");   
    }
}

async function getId(username) {
    const response4 = await fetch('http://localhost:8080/users/getIdByLogin/' + username);
        const text4 = await response4.text();
        const id = parseInt(text4);
        localStorage.setItem('userNowId', id);
}

async function addCart(){
    const id = localStorage.getItem('userNowId');

        const response5 = await fetch('http://localhost:8080/cart', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            user: {
              id: id
            },
            products: []
          })
    })
    //if (response5.status === 200) {
      //  alert("УРАА");
    //}
}