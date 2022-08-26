# SpacePortalBackend
This is a Spring Boot REST/JPA demo, used in educational purposes.

The app creates four REST entrypoints:
- http://localhost:8080/api/v1/customers
- http://localhost:8080/api/v1/planets
- http://localhost:8080/api/v1/spaceships
- http://localhost:8080/api/v1/reservations

The data comes from a H2 memory database, with the entity tables, data and join tables:
- Customer
- Planet + PlanetType
- Spaceship
- Reservation

A customer can have many reservations
A reservation can have many customers/travelers
Customer and Reservation have a many to many relationship

The controllers primarily only exposes DTOs - Data Transfer Objects, not the original entities, to enable handling different external formats (JSON, XML...).
So each entity class has a corresponding entityDto class, which also manages the @JsonBackReference and @JsonBackReference.
