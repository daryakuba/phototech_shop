async function showOrders() {
  fetch('http://127.0.0.1:8080/orders')
  .then(response => response.json())
  .then(data => {
    const template = Handlebars.compile(document.querySelector('#orders').innerHTML);
    const html = template({ orders: data });
    document.querySelector('#orders-list').innerHTML = html;
  });
}

  async function deleteOrder(button) {
    var orderId = button.getAttribute("data-id");
    
    
    fetch('http://127.0.0.1:8080/orders/' + orderId, {
        method: 'DELETE'
      })
      .then(response => {
        if (response.ok) {
          alert("Заказ удален")
        } else {
          alert("Ошибка")
        }
      })
}