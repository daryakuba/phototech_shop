const urlParams = new URLSearchParams(window.location.search);
const id = urlParams.get('id');

fetch('http://127.0.0.1:8080/products/' + id)
  .then(response => response.json())
  .then(data => {
    const template = Handlebars.compile(document.querySelector('#products').innerHTML);
    const html = template({ products: data });
    document.querySelector('#products-list').innerHTML = html;
  });
  
