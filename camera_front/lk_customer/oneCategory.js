const urlParams = new URLSearchParams(window.location.search);
const id = urlParams.get('id');

fetch('http://127.0.0.1:8080/products/' + id)
  .then(response => response.json())
  .then(data => {
    const template = Handlebars.compile(document.querySelector('#products').innerHTML);
    const html = template({ products: data });
    document.querySelector('#products-list').innerHTML = html;
  });

  function hideButton(event) {
    var button = event.target;
    var productStatus = button.getAttribute("data-status");
    if (productStatus === 'нет в наличии') {
      event.preventDefault(); // отменяем действие
      alert("Товара нет в наличии");
    }
  }

  async function addToCart() {

    const urlParams = new URLSearchParams(window.location.search);
    const product_id = urlParams.get('product_id');

    alert(product_id);

    const user_id = localStorage.getItem('userNowId')
    let response2 = await fetch('http://localhost:8080/cart/getByUser/' + user_id);
    text1 = await response2.text();
    const cart_id = parseInt(text1);

    let response3 = await fetch('http://localhost:8080/cart/addProduct/' + cart_id + '/' + product_id);
    
    if (response3.ok) {
        alert("Товар добавлен в коризну")
    } 
  }

  
