# SpacePortalBackend
This is a Spring Boot REST/JPA backend demo, made for educational purposes. The frontend is [SpacePortalFrontend](https://github.com/RonniKahalani/SpacePortalFrontend)

![image](https://user-images.githubusercontent.com/8819076/186982448-eb34f465-60ae-4706-81d6-263b153c9b6c.png)

### Technologies
- Java 17
- Spring Boot 2.7.2

### Purpose
Create a backend serving Json-based data via REST services. Serving the frontend [SpacePortalFrontend](https://github.com/RonniKahalani/SpacePortalFrontend)

The app creates four REST API endpoints:
- /api/v1/customers
- /api/v1/planets
- /api/v1/spaceships
- /api/v1/reservations

The data comes from a H2 memory database, with the entity tables, data and join tables:
- Customers
- Planets + PlanetTypes
- Spaceships
- Reservations
