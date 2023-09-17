fetch('http://localhost:8082/Paperless/main')
  .then((response) => response.json())
  .then((data) => console.log(data));
