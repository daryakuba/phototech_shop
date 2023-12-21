async function changeStatus() {
    const urlParams = new URLSearchParams(window.location.search);
    const order_id = urlParams.get('id');

    var newStatus = document.getElementById("status_new").value;

    const response = await fetch('http://localhost:8080/orders/updStatus/' + order_id + '/' + newStatus);
if(response.ok){
    alert("Статус изменен");
}
}