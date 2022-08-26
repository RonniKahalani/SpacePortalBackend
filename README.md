# SpacePortalBackend
![image](https://user-images.githubusercontent.com/8819076/186982448-eb34f465-60ae-4706-81d6-263b153c9b6c.png)

This is a Spring Boot REST/JPA backend demo, used in educational purposes.

The app creates four REST API entry points:
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
So each entity class has a corresponding entityDto class, which also manages the following Json annotations, to control and avoid recursive stack overflows.  
- [@JsonBackReference](https://www.tutorialspoint.com/jackson_annotations/jackson_annotations_jsonbackreference.htm) is the back part of reference – it will be omitted from serialization.
- [@JsonManagedReference](https://www.tutorialspoint.com/jackson_annotations/jackson_annotations_jsonmanagedreference.htm) is the forward part of reference – the one that gets serialized normally.
@JsonManagedReference is the forward part of reference – the one that gets serialized normally. @JsonBackReference is the back part of reference – it will be omitted from serialization.
