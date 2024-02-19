# School Payment System

Welcome to the School Payment System repository! This project is developed for The Software Partner's recruitment task. The primary module created is the Payment Settlement service, which handles retrieving payment settlements for schools and parents.

## Overview

The School Payment System focuses on retrieving payment settlements for schools and parents. It provides endpoints to fetch payment settlements specific to schools and parents, facilitating efficient management of financial transactions within educational institutions. The project follows the principles of Domain Driven Development (DDD) to ensure a robust and maintainable architecture.

## Technologies Used

- **Java**: The primary programming language used for the project.
- **Spring Boot**: Provides a framework for building robust and scalable applications.
- **Domain Driven Development (DDD)**: Follows DDD principles for designing the architecture.
- **Dependencies**:
    - `com.school.payment.system:payment-settlement-domain-core` (Handles business logic)
    - `com.school.payment.system:payment-settlement-application-service` (Handles input ports and application logic, also contains unit tests)
    - `com.school.payment.system:payment-settlement-application` (Handles controllers and input port implementations for connecting to app)
    - `com.school.payment.system:payment-settlement-dataaccess` (Handles data access to database)
    - `com.school.payment.system:payment-settlement-container` (Runs Spring app and creates required configs and beans)
    - `com.school.payment.system:common-application` (Contains common structures, that can be reusable for other services)
    - `org.springdoc:springdoc-openapi-starter-webmvc-ui` (Swagger for API documentation)
    - `org.projectlombok:lombok` (for reducing boilerplate code)
    - `org.springframework.boot:spring-boot-starter-logging` (for logging purposes)
    - `org.springframework.boot:spring-boot-starter-validation` (for validation purposes)
    - `org.springframework.boot:spring-boot-starter-test` (with scope `test`, for testing purposes)
    - `com.h2database:h2` (for testing purposes)
    - `org.springframework.boot:spring-boot-starter-data-jpa` (Enables Spring Data Jpa, Hibernate and connecting to db)
    - `org.postgresql:postgresql` (Enables connecting to Postgresql)
    - `org.springframework.boot:spring-boot-starter-web` (Allows creation of REST controllers)

## Endpoints

### /api/v1/payment/settlements/school

This endpoint is used for retrieving payment settlements for schools.

#### Example Request

```
GET /api/v1/payment/settlements/school?schoolId=d215b5f8-0249-4dc5-89a3-51fd148cfb21&year=2022&month=October
```

#### Example Response

```json
{
    "schoolName": "High school nr. 1",
    "fee": 150.00,
    "children": [
        {
            "firstName": "Margaret",
            "lastName": "Loother",
            "fee": 50.00
        },
        {
            "firstName": "Kendrick",
            "lastName": "Obama",
            "fee": 50.00
        },
        {
            "firstName": "Michelle",
            "lastName": "Obama",
            "fee": 50.00
        }
    ]
}
```

### /api/v1/payment/settlements/parent

This endpoint is used for retrieving payment settlements for parents.

#### Example Request

```
GET /api/v1/payment/settlements/parent?parentId=d215b5f8-0249-4dc5-89a3-51fd148cfb26&year=2022&month=October
```

#### Example Response

```json
{
    "firstName": "Barack",
    "lastName": "Obama",
    "fee": 100.00,
    "children": [
        {
            "firstName": "Kendrick",
            "lastName": "Obama",
            "fee": 50.00,
            "school": {
                "name": "High school nr. 1"
            }
        },
        {
            "firstName": "Michelle",
            "lastName": "Obama",
            "fee": 50.00,
            "school": {
                "name": "High school nr. 1"
            }
        }
    ]
}
```
