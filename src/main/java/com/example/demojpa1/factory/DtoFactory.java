package com.example.demojpa1.factory;

import com.example.demojpa1.DemoJpa1Application;
import com.example.demojpa1.customer.model.Customer;
import com.example.demojpa1.dto.*;
import com.example.demojpa1.planet.model.Planet;
import com.example.demojpa1.planet.model.PlanetType;
import com.example.demojpa1.reservation.model.Reservation;
import com.example.demojpa1.spaceship.model.Spaceship;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DtoFactory {

    private static ModelMapper modelMapper = DemoJpa1Application.modelMapper();

    /*
        Customers
     */
    public static CustomerDto fromCustomer(Customer customer) {
        CustomerDto dto = new CustomerDto();
        dto.setId(customer.getId());
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        return dto;
    }

    public static Customer fromCustomerDto(CustomerDto dto) {
        Customer customer = new Customer();
        if (dto.getId() != null) {
            customer.setId(dto.getId());
        }
        if (dto.getFirstName() != null) {
            customer.setFirstName(dto.getFirstName());
        }
        if (dto.getLastName() != null) {
            customer.setLastName(dto.getLastName());
        }
        return customer;
    }

    public static List<CustomerDto> fromCustomers(List<Customer> customers) {
        return customers.stream().map(obj -> fromCustomer(obj)).collect(Collectors.toList());
    }

    public static List<Customer> fromCustomerDtos(List<CustomerDto> customerDtos) {
        return customerDtos.stream().map(obj -> modelMapper.map(obj, Customer.class))
                .collect(Collectors.toList());
    }

    /*
        Planets
     */
    public static PlanetDto fromPlanet(Planet planet) {
        PlanetDto dto = new PlanetDto();
        dto.setId(planet.getId());
        dto.setDensity(planet.getDensity());
        dto.setDiameter(planet.getDiameter());
        dto.setDistanceFromSun(planet.getDistanceFromSun());
        dto.setGravity(planet.getGravity());
        dto.setHoursPerDay(planet.getHoursPerDay());
        dto.setMass(planet.getMass());
        dto.setMoons(planet.getMoons());
        dto.setMeanTemp(planet.getMeanTemp());
        dto.setName(planet.getName());
        dto.setNotes(planet.getNotes());
        dto.setImageUrl(planet.getImageUrl());
        dto.setVideoUrl(planet.getVideoUrl());
        dto.setThumbnailUrl(planet.getThumbnailUrl());

        if (planet.getTypes() == null) {
            dto.setTypes(null);
        } else {
            dto.setTypes(planet.getTypes().stream().map(obj -> fromPlanetType(obj)).collect(Collectors.toList()));
        }
        return dto;
    }

    public static Planet fromPlanetDto(PlanetDto dto) {
        Planet planet = new Planet();
        planet.setId(dto.getId());
        planet.setDensity(dto.getDensity());
        planet.setDiameter(dto.getDiameter());
        planet.setDistanceFromSun(dto.getDistanceFromSun());
        planet.setGravity(dto.getGravity());
        planet.setHoursPerDay(dto.getHoursPerDay());
        planet.setMass(dto.getMass());
        planet.setMoons(dto.getMoons());
        planet.setMeanTemp(dto.getMeanTemp());
        planet.setName(dto.getName());
        planet.setNotes(dto.getNotes());
        planet.setImageUrl(dto.getImageUrl());
        planet.setVideoUrl(dto.getVideoUrl());
        planet.setThumbnailUrl(dto.getThumbnailUrl());

        if (dto.getTypes() == null) {
            planet.setTypes(null);
        } else {
            planet.setTypes(dto.getTypes().stream().map(obj -> fromPlanetTypeDto(obj)).collect(Collectors.toList()));
        }
        return planet;
    }

    public static List<PlanetDto> fromPlanets(List<Planet> planets) {
        return planets.stream().map(obj -> fromPlanet(obj))
                .collect(Collectors.toList());
    }

    public static List<Planet> fromPlanetDtos(List<PlanetDto> planetDtos) {
        return planetDtos.stream().map(obj -> fromPlanetDto(obj))
                .collect(Collectors.toList());
    }

    /*
    PlanetTypes
 */
    public static PlanetTypeDto fromPlanetType(PlanetType planetType) {
        PlanetTypeDto dto = new PlanetTypeDto();
        dto.setId(planetType.getId());
        dto.setName(planetType.getName());
        return dto;
    }

    public static PlanetType fromPlanetTypeDto(PlanetTypeDto dto) {
        PlanetType planetType = new PlanetType();
        planetType.setId(dto.getId());
        planetType.setName(dto.getName());

        return planetType;
    }

    public static List<PlanetTypeDto> fromPlanetTypes(List<PlanetType> planetTypes) {
        return planetTypes.stream().map(obj -> fromPlanetType(obj))
                .collect(Collectors.toList());
    }

    public static List<PlanetType> fromPlanetTypeDtos(List<PlanetTypeDto> planetTypeDtos) {
        return planetTypeDtos.stream().map(obj -> fromPlanetTypeDto(obj))
                .collect(Collectors.toList());
    }

    /*
        Spaceships
     */
    public static SpaceshipDto fromSpaceship(Spaceship spaceship) {
        SpaceshipDto dto = new SpaceshipDto();
        dto.setId(spaceship.getId());
        dto.setName(spaceship.getName());
        dto.setBuildDate(spaceship.getBuildDate());
        dto.setImageUrl(spaceship.getImageUrl());
        dto.setVideoUrl(spaceship.getVideoUrl());
        dto.setThumbnailUrl(spaceship.getThumbnailUrl());
        dto.setMaxLoad(spaceship.getMaxLoad());
        dto.setMaxPassengers(spaceship.getMaxPassengers());
        dto.setMaxSpeed(spaceship.getMaxSpeed());
        return dto;
    }

    public static Spaceship fromSpaceshipDto(SpaceshipDto dto) {
        Spaceship spaceship = new Spaceship();
        spaceship.setId(dto.getId());
        spaceship.setName(dto.getName());
        spaceship.setBuildDate(dto.getBuildDate());
        spaceship.setImageUrl(dto.getImageUrl());
        spaceship.setVideoUrl(dto.getVideoUrl());
        spaceship.setThumbnailUrl(dto.getThumbnailUrl());
        spaceship.setMaxLoad(dto.getMaxLoad());
        spaceship.setMaxPassengers(dto.getMaxPassengers());
        spaceship.setMaxSpeed(dto.getMaxSpeed());
        return spaceship;
    }

    public static List<SpaceshipDto> fromSpaceships(List<Spaceship> spaceships) {
        return spaceships.stream().map(obj -> fromSpaceship(obj))
                .collect(Collectors.toList());
    }

    public static List<Spaceship> fromSpaceshipDtos(List<SpaceshipDto> spaceshipDtos) {
        return spaceshipDtos.stream().map(obj -> fromSpaceshipDto(obj))
                .collect(Collectors.toList());
    }

    /*
      Reservations
   */
    public static ReservationDto fromReservation(Reservation reservation) {
        ReservationDto dto = new ReservationDto();
        dto.setId(reservation.getId());
        dto.setStartDate(reservation.getStartDate());
        dto.setEndDate(reservation.getEndDate());
        dto.setPlanet(fromPlanet(reservation.getPlanet()));
        dto.setSpaceship(fromSpaceship(reservation.getSpaceship()));
        dto.setCustomers(fromCustomers(reservation.getCustomers()));
        return dto;
    }

    public static Reservation fromReservationDto(ReservationDto dto) {
        Reservation reservation = new Reservation();
        reservation.setId(dto.getId());
        reservation.setStartDate(dto.getStartDate());
        reservation.setEndDate(dto.getEndDate());
        reservation.setPlanet(fromPlanetDto(dto.getPlanet()));
        reservation.setSpaceship(fromSpaceshipDto(dto.getSpaceship()));
        reservation.setCustomers(fromCustomerDtos(dto.getCustomers()));
        return reservation;
    }

    public static List<ReservationDto> fromReservations(List<Reservation> reservations) {
        return reservations.stream().map(obj -> fromReservation(obj))
                .collect(Collectors.toList());
    }

    public static List<Reservation> fromReservationDtos(List<ReservationDto> reservationDtos) {
        return reservationDtos.stream().map(obj -> fromReservationDto(obj))
                .collect(Collectors.toList());
    }

}
