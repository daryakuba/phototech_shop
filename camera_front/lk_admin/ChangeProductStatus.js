async function changeStatus() {
    const urlParams = new URLSearchParams(window.location.search);
const product_id = urlParams.get('id');

    var newStatus = document.getElementById("status_new").value;

    const response = await fetch('http://localhost:8080/products/updStatus/' + product_id + '/' + newStatus);
if(response.ok){
    alert("Статус изменен");
}
}