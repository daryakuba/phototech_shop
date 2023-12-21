async function addToCart() {
  const urlParams = new URLSearchParams(window.location.search);
  const product_id = urlParams.get('product_id');

  const user_id = localStorage.getItem('userNowId')
  let response2 = await fetch('http://localhost:8080/cart/getByUser/' + user_id);
  text1 = await response2.text();
  const cart_id = parseInt(text1);

  let response3 = await fetch('http://localhost:8080/cart/addProduct/' + cart_id + '/' + product_id);
  
  if (response3.ok) {
      alert("Товар добавлен в корзину")
  } 
}

async function showCart(){
  const user_id = localStorage.getItem('userNowId');

    let response3 = await fetch('/cart/isCartExist/' + user_id);
    let text2 = await response3.text();

    let response4 = await fetch('http://localhost:8080/cart/getByUser/' + user_id);
    const text4 = await response4.text();
    const cart_id = parseInt(text4);

    let response2 = await fetch('http://localhost:8080/cart/getProducts/' + cart_id);
    if (response2.ok){
      fetch('http://localhost:8080/cart/getProducts/' + cart_id)
      .then(response => response.json())
      .then(data => {
        const template = Handlebars.compile(document.querySelector('#products_in_cart').innerHTML);
        const html = template({ products_in_cart: data });
        document.querySelector('#products_in_cart-list').innerHTML = html;
      });
    }
}

async function deleteProduct(product_id){
  const user_id = localStorage.getItem('userNowId')
  let response2 = await fetch('http://localhost:8080/cart/getByUser/' + user_id);
  text1 = await response2.text();
  const cart_id = parseInt(text1);
  
  let response3 = await fetch('http://localhost:8080/cart/removeProduct/' + cart_id + '/' + product_id);
  
  if (response3.ok) {
      alert("Товар удален из корзины")
  } 
  location.reload();
}


  