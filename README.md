# SpacePortalBackend
This is a Spring Boot REST/JPA backend demo, made for educational purposes. The frontend is [SpacePortalFrontend](https://github.com/RonniKahalani/SpacePortalFrontend)

![image](https://user-images.githubusercontent.com/8819076/186982448-eb34f465-60ae-4706-81d6-263b153c9b6c.png)

### Purpose
Create a backend serving Json-based data via REST services. Serving the frontend [SpacePortalFrontend](https://github.com/RonniKahalani/SpacePortalFrontend)

The app creates four REST API endpoints:
- /api/v1/customers
- /api/v1/planets
- /api/v1/spaceships
- /api/v1/reservations

The data comes from a H2 memory database, with the entity tables, data and join tables:
- Customer
- Planet + PlanetType
- Spaceship
- Reservation

A customer can have many reservations
A reservation can have many customers/travelers
Customer and Reservation have a many-to-many relationship.

The controllers primarily only exposes DTOs - Data Transfer Objects, not the original entities, to enable handling different external formats (JSON, XML...).
So each entity class has a corresponding entityDto class. 

The Dtos uses the following Json annotations, to control and avoid recursive stack overflows because of circular relation references.
Example: customer references reservation, which references back to customer.....Now we're running in circles, which result in stack owerflow.  
- [@JsonBackReference](https://www.tutorialspoint.com/jackson_annotations/jackson_annotations_jsonbackreference.htm) is the back part of reference – it will be omitted from serialization.
- [@JsonManagedReference](https://www.tutorialspoint.com/jackson_annotations/jackson_annotations_jsonmanagedreference.htm) is the forward part of reference – the one that gets serialized normally.
