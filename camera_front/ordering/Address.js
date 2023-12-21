async function createOrder() {
    var address = document.getElementById("address_new").value;

    const user_id = localStorage.getItem("userNowId");

    let response1 = await fetch('http://localhost:8080/cart/getByUser/' + user_id);
    const text1 = await response1.text();
    const cart_id = parseInt(text1);

    await fetch('http://localhost:8080/cart/getProducts/' + cart_id)
    .then(response => response.json())
    .then(data => {
        setProducts(data, user_id, address)});

    cleanCart(cart_id);
}

async function setProducts(products, user_id, address) {
    const response3 = await fetch('http://localhost:8080/orders', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            status: "Создан",
            address: address,
            user: {
                id: user_id
            },
            products: products
        })
    })
    if(response3.ok){
        alert("Заказ создан");
    }
}

async function cleanCart(cart_id){
const response = await fetch('http://localhost:8080/cart/updProducts/' + cart_id);
if(response.ok){
    alert("Корзина очищена");
}
}

