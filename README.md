# astronaut-info-service
This API microservice is implemented to provide Astronaut information.

## What's configured
- Spring boot starter
- Basic Spring boot Actuator
- Lombok
- Junit tests

## How to run it locally
To Build the package execute below command 
```sh
mvn package
```
To Run the service execute below command
```sh
mvn spring-boot:run
```
### Test locally
Application will run on port `9551` with context path `/astronaut-info`

API to fetch all Astronaut list 
```sh
http://localhost:9551/astronaut-info/api/v1/astronaut
```

API to fetch data for specific Astronaut - 
```sh
http://localhost:9551/astronaut-info/api/v1/astronaut/{id}
```

API to fetch the health of service (Actuator) - 
```sh
http://localhost:9551/astronaut-info/actuator/health
```

## Structure

Code is structred in the following way under package

`com.suncorp.astronautinfo`

| Package | Description |
| ------ | ------ |
|`rest.astronaut`|astronaut controller and service classes|
|`rest.model`|astronaut data classes|
|`core`|core functionalities like exception/error classes|

### REST API Class Structure

- `AstronautController` - REST controller for APIs
- `AstronautService` - Service class for API. This is where interaction with `spacelaunchnow.me/api/3.5.0/astronaut/` is made to fetch astronaut details
- `AstronautDto` - Data Transfer Object for API `Response`
- `ErrorHandler` - For handling error scenarios
