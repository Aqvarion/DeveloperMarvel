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

The project includes **swagger**. It provides more detailed information of all requests. 
You need to start the project and follow [link](http://localhost:8080/swagger-ui/index.html)

### Docker

The project can be run as a Docker container. For this:
1. Install [Docker](https://docs.docker.com/docker-for-windows/install/)
2. Build the project if you have not built it yet 
3. Collect the project image `docker build -t imagename .` or download the image from 
   the [link](https://hub.docker.com/layers/152871320/blackapple789/defaultrepository/marvelapi/images/sha256-54187b7164d1dbe27759761ba3aacb7c5a254e7f239381bacc85e39e29b8d1e9?context=explore)
   1. If there is a problem with the database - in *application.properties*
      change `jdbc:mysql://192.168.0.60:3306/webmarvel` *192.168.0.60* to ipv4 of your machine
4. Start the container `docker run -p 8080:8080 imagename`

### Authors

For communication and questions, you can write to the mail:
black.game.apple789@mail.ru