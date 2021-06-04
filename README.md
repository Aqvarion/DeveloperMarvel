# API for Marvel Website

A simple API that provides CRUD operations for the user interface.
### Environment 
JDK 1.8 +</br>
Maven 3.6.3 +</br>
IntelliJ IDEA 2021.1 (Ultimate Edition) +</br>
MySql 8.0 +</br>

### Get starting

In order to use this project, you must:
1. Download this project
2. Use command `mvn package` to compile the project and place it in the folder *target*

### List of requests

I will give a list of implemented requests for working with part of the **comics**,
for part of **characters** all requests are similar.

GET method `http://localhost:8080/v1/public/comics` - 
allows you to get a list of comics, with their properties (id, title, author, published, descript, image).

GET method `http://localhost:8080/v1/public/comics/{{comicId}}` -
allows you to get a specific comic by id.

GET method `http://localhost:8080/v1/public/comics/{{comicId}}/characters` -
allows you to get a list of characters contained in a specific comic by ID.

POST method `http://localhost:8080/v1/public/comics` - allows you to add a new comic to the database.
The request must contain a part **comic** with main data, and **img** part with an image file.

PUT method `http://localhost:8080/v1/public/comics/{{comicId}}` -
allows you to modify an existing comic by ID. Contains two parts **comic** and **img**.

DELETE method `http://localhost:8080/v1/public/comics/{{comicId}}`
delete the existing comic by ID.   

### Authors

For communication and questions, you can write to the mail:
black.game.apple789@mail.ru