async function showActive(){
    const user_id = localStorage.getItem('userNowId');

    let response2 = await fetch('http://localhost:8080/orders/getActive/' + user_id);
    if (response2.ok){
fetch('http://localhost:8080/orders/getActive/' + user_id)
.then(response => response.json())
.then(data => {
const template = Handlebars.compile(document.querySelector('#active').innerHTML);
const html = template({ active: data });
document.querySelector('#active-list').innerHTML = html;
});
}
}