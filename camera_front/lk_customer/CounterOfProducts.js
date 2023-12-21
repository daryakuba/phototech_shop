async function addToCart() {
    const urlParams = new URLSearchParams(window.location.search);
    const product_id = urlParams.get('product_id');
  
    const user_id = localStorage.getItem('userNowId')
    let response2 = await fetch('http://localhost:8080/cart/getByUser/' + user_id);
    text1 = await response2.text();
    const cart_id = parseInt(text1);
  
    let response3 = await fetch('http://localhost:8080/cart/addProduct/' + cart_id + '/' + product_id);
    
    if (response3.ok) {
        alert("Товар добавлен в коризну")
    } 
    }