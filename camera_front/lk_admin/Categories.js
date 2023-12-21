fetch('http://127.0.0.1:8080/categories')
  .then(response => response.json())
  .then(data => {
    const template = Handlebars.compile(document.querySelector('#categories').innerHTML);
    const html = template({ categories: data });
    document.querySelector('#categories-list').innerHTML = html;
  });

