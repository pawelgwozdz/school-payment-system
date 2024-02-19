# School Payment System

Welcome to the School Payment System repository! This project is developed for The Software Partner's recruitment task. The primary module created is the Payment Settlement service, which handles retrieving payment settlements for schools and parents.

## Overview

The School Payment System focuses on retrieving payment settlements for schools and parents. It provides endpoints to fetch payment settlements specific to schools and parents, facilitating efficient management of financial transactions within educational institutions.

## Technologies Used

- **Java**: The primary programming language used for the project.
- **Spring Boot**: Provides a framework for building robust and scalable applications.
- **Swagger**: The API documentation is available using Swagger UI at [http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/).

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
