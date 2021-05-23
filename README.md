# Credit Card Processing

This micro service provides a RESTful API to process credit cards with two endpoints:

- POST -> Creates a new credit card for a given name, card number, and limit.
  - Card numbers are validated using Luhn 10.
  - New cards start with a £0 balance, unless specified in request.
- GET -> Returns all cards in the system.

## Getting started

You should build and install the  micro service in order to run it:
```
mvn clean install
```

The following command line runs the service locally:
```
mvn spring-boot:run
```

## Open API documentation

When running the service, Swagger documentation can be found at:
http://localhost:8080/documentation/swagger-ui/
and
http://localhost:8080/v3/api-docs
