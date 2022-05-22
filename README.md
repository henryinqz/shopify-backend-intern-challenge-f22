# Shopify Backend Intern Challenge (Fall 2022)
This inventory tracking web application was created for the [Shopify Backend Developer Intern Challenge](https://docs.google.com/document/d/1PoxpoaJymXmFB3iCMhGL6js-ibht7GO_DkCF2elCySU/edit?usp=sharing).

## Features
- Basic CRUD Functionality
  - Create Inventory Items
  - Edit Them
  - Delete Them
  - View a list of them

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

| Method   | Endpoint             | Description                                                                                                                                         |
|----------|----------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------|
| `GET`    | `/inventory/list`    | Returns a JSON array of all entries in the `inventory` collection                                                                                   |
| `POST`   | `/inventory/create` | Takes a single JSON object (ex. `{name: "Entry Name", quantity: 1}`) as the request body and create a new entry in the`inventory` collection        |
| `POST`   | `/inventory/{id}`    | Takes a single JSON object (ex. `{name: "Updated Name", quantity: 3}`) as the request body and updates `inventory` collection entry with given `id` |
| `DELETE` | `/inventory/{id}}`   | Removes `inventory` collection entry with given `id`                                                               |

