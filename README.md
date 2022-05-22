# Shopify Backend Intern Challenge (Fall 2022)
This inventory tracking web application was created for the [Shopify Backend Developer Intern Challenge](https://docs.google.com/document/d/1PoxpoaJymXmFB3iCMhGL6js-ibht7GO_DkCF2elCySU/edit?usp=sharing).

## Features
- Basic CRUD Functionality
  - Create Inventory Items
  - Edit Them
  - Delete Them
  - View a list of them
- Ability to create warehouses/locations and assign inventory to specific locations

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
| Method   | Endpoint            | Description                                                                                             | Example Body                            |
|----------|---------------------|---------------------------------------------------------------------------------------------------------|-----------------------------------------|
| `GET`    | `/inventory/list`   | Returns a JSON array of all entries in the `inventory` collection                                       |
| `POST`   | `/inventory/create` | Takes a single JSON object as the request body and create a new entry in the `inventory` collection     | `{name: "Entry Name", quantity: 50}`    |
| `PUT`    | `/inventory/{id}`   | Takes a single JSON object as the request body and updates `inventory` collection entry with given `id` | `{name: "Updated Name", quantity: 300}` |
| `DELETE` | `/inventory/{id}}`  | Removes entry from `inventory` collection with given `id`                                               |
### Locations
| Method   | Endpoint            | Description                                                                                             | Example Body                                                                                                               |
|----------|---------------------|---------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------|
| `GET`    | `/locations/list`   | Returns a JSON array of all entries in the `location` collection                                        |
| `POST`   | `/locations/create` | Takes a single JSON object as the request body and create a new entry in the `location` collection      | `{name: "YYZ Warehouse", address: "290 Bremner Blvd", zipCode: "M5V 3L9", city: "Toronto", state: "ON" country: "Canada"}` |
| `PUT`    | `/locations/{id}`   | Takes a single JSON object as the request body and updates `location` collection entry with given `id`  | `{name: "ATL Store", address: "55 Trinity Ave SW", zipCode: "303030", city: "Atlanta", state: "GA", country: "USA"`        |
| `DELETE` | `/locations/{id}}`  | Removes entry from `location` collection with given `id`                                                |

