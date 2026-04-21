# Client-Server Architectures Coursework

## API Design Overview

This API is designed to manage a "Smart Campus" system, providing access to rooms, sensors and sensor readings by adhering to RESTful principles.

### Endpoints

The API is organised around three main resources:

`/rooms` - for managing rooms <br>
`/sensors` - for managing sensors <br>
`/sensors/{id}/readings` - for managing sensor readings from a specific sensor

### Features

- Supports `GET`, `POST` and `DELETE` operations
- Validates logic before carrying out requests (e.g. sensors must belong to a room)
- Utilises HTTP status codes with structured responses

### Running The Project

1. Download and open the project in your IDE
2. Ensure the server (e.g. Apache Tomcat) is running
3. Build and run the project
4. Open your browser and go to [http://localhost:8080/Smart-Campus-api/api/v1/](http://localhost:8080/Smart-Campus-api/api/v1/) or Postman to access the API

## Part 1

Question 1:

The default lifecycle of a JAX-RS Resource class is “per-request”, meaning a new instance of the class for each request it receives. Using a Resource class with its default lifecycle allows us to utilise ArrayLists and HashMaps without worrying about race conditions as we know that only the request thread, the instance of the Resource class currently in use, can access that data structure. This however also means that storing data between requests is not possible, this is due to the data structures having their data reset to whatever their initial states were. This also applies to static objects as upon a server restart their data is also lost.

Question 2:

Using Hypermedia allows the API to become essentially self-guiding, hardcoded URLs are no longer needed and there aren’t any errors if a developer alters the path, the client application does not break and the server provides the new URL to the user instead. It also removes the need for the user to know each endpoint in advance by using Hypermedia to navigate to the page they want. Hypermedia also allows business logic to be server-side instead of client-side, preventing options from being accessible if they are not available, stopping users from carrying out invalid operations.

## Part 2

Question 1:

Returning a list of the full room objects takes up more bandwidth and would need more processing time from the client, leading to slower responses to the client’s requests. Returning only the IDs of the room objects instead would have significantly lower bandwidth usage, especially if there are a large number of sensors per room, and need much less client side processing, which means faster responses to the requests. However, returning only the IDs means omitting other data from the initial request, leading to more requests for specific data from the room objects. So, while for the initial response returning just IDs would be significantly faster, there is also the extra number of requests needed to return more data from those objects to take into account.

Question 2:

Yes, the DELETE operation is idempotent. After the client sends the first request for deletion of that room it is either deleted or the client is told that it cannot be deleted as it currently has sensors assigned to it, if this is the case then this will be the result for all subsequent DELETE requests until the sensors are removed. For the prior outcome, the room is deleted successfully and any further requests then prompt a NotFoundException to be thrown, stating the id of the room that cannot be found.

## Part 3

Question 1:

JAX-RS would first attempt to find a POST method that can utilise that data format, identified by the Content-Type header of the request, which it will not be able to. It will then return a HTTP 415 Unsupported Media Type error and as this happens during the routing phase the resource class is not instantiated, which ensures the server only handles the data formats it is able to.

Question 2:

Using query parameters is considered superior as path parameters force a hierarchical structure on the URL which becomes more difficult to manage with an increasing number of filters. Query parameters allow for inherently optional and unordered filters, allowing for the same path to be used on a single method with filters that only apply when the user makes use of them.

## Part 4

Question 1:

Using a Sub-Resource Locator pattern to delegate logic to other classes prevents a single class from having too many nested paths, improving readability and debugging. It also allows for reusability of code, utilising the SensorReadingResource class in another resource class in the future.

## Part 5

Question 1:

HTTP 404 is for errors in the URI, where the client uses a path that is not implemented so the endpoint is missing. HTTP 422 error is for logical errors, where the URI is correct but the information the request contains is invalid, such as a non-existent room ID, and therefore making it unprocessable.

Question 2:

Exposing stack traces leaks information about the application, often software and library versions, file structure and possibly database queries, which can allow attackers to identify known vulnerabilities or acquire more information about the infrastructure of the application. This information can then be used by attackers to refine their attacks, targeting specific sections of code to find a weak point.

Question 3:

Manually inserting Logger.info() inside each resource method would lead to decreased readability of the code while also making logging format changes incredibly time consuming. Using a filter would instead allow for changes to be applied quickly and easily while also preserving the readability of the resource class’ code.


