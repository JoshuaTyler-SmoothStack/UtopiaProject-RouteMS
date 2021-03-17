# RouteMS
Route Microservice for Utopia Airlines
## Requirements & Quick Start
##### -Maven
##### -MySQL
`$ mvn spring-boot:run` - run RouteMS as a spring boot application. The application will run by default on port `8088`.

Configure the port by changing the `server.port` value in the `application.properties` file which is located in the resources folder.

The application can be run with a local MySQL database. Configure the `spring.datasource.url`, `spring.datasource.username`, and `spring.datasource.password` in the `application.properties` file according to your needs.
## API
`/routes` - GET : Get a list of all the routes from the DB.

`/routes/{id}` - GET : Get a route by id.

`/routes` - POST : Create a route by providing a correct request body

`/routes` - PUT : Update a route by providing a correct request body including the id

`/routes/{id}` - DELETE : Delete a route by id.
