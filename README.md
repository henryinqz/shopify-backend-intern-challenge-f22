# Shopify Backend Intern Challenge (Fall 2022)
This inventory tracking web application was created for the [Shopify Backend Developer Intern Challenge](https://docs.google.com/document/d/1PoxpoaJymXmFB3iCMhGL6js-ibht7GO_DkCF2elCySU/edit?usp=sharing).

## Features
- Basic CRUD Functionality
  - Create Inventory Items
  - Edit Them
  - Delete Them
  - View a list of them
- Ability to create warehouses/locations and assign inventory to specific locations
  - Deleting a location will delete all inventory entries associated with that location  

## Stack
- Spring Boot
  - Thymeleaf for basic HTML/CSS template
- MongoDB

## Environment
Create the following config at `src/main/resources/application.properties`:
```
spring.data.mongodb.uri=your MongoDB uri
spring.data.mongodb.database=your MongoDB database name
```

## Endpoints
### Inventory
| Method   | Endpoint              | Description                                                                                                                                                       | Example Body                                                                          |
|----------|-----------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------|
| `GET`    | `/api/inventory`      | Returns a JSON array of all entries in the `inventory` collection                                                                                                 |
| `POST`   | `/api/inventory`      | Takes a single JSON object as the request body and returns a JSON array of entries in the `inventory` collection stored at a `location` specified by `locationId` | `{"locationId": "628a868059a10309f7f35172"}`|
| `GET`    | `/api/inventory/{id}` | Returns a single JSON object of the `inventory` entry with given `id`                                                                                             |
| `POST`   | `/api/inventory/create`   | Takes a single JSON object as the request body and create a new entry in the `inventory` collection                                                               | `{"name": "Apple iPhone 13", "quantity": "50", "locationId": "628a868059a10309f7f35172"}`     |
| `PUT`    | `/api/inventory/{id}`     | Takes a single JSON object as the request body and updates `inventory` collection entry with given `id`                                                           | `{"name": "Samsung Galaxy S22", "quantity": "300", "locationId": "628aa8719e86ef7e8d6bc6e3"}` |
| `DELETE` | `/api/inventory/{id}`     | Removes entry from `inventory` collection with given `id`                                                                                                         |
### Locations
| Method   | Endpoint           | Description                                                                                                 | Example Body                                                                                                                                  |
|----------|--------------------|-------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------|
| `GET`    | `/api/locations`  | Returns a JSON array of all entries in the `location` collection                                            |
| `GET`    | `/api/locations/{id}`  | Returns a single JSON object of the `location` entry with given `id` |
| `POST`   | `/api/locations/create` | Takes a single JSON object as the request body and create a new entry in the `location` collection          | `{"name": "YYZ Warehouse", "address": "290 Bremner Blvd", "zipCode": "M5V 3L9", "city": "Toronto", "state": "Ontario" "country": "Canada"}`             |
| `PUT`    | `/api/locations/{id}`  | Takes a single JSON object as the request body and updates `location` collection entry with given `id`      | `{"name": "ATL Store", "address": "55 Trinity Ave SW", "zipCode": "303030", "city": "Atlanta", "state": "Georgia", "country": "United States of America"` |
| `DELETE` | `/api/locations/{id}`  | Removes entry from `location` collection with given `id`                                                    |

