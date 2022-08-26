package com.example.demojpa1.factory;

import com.example.demojpa1.DemoJpa1Application;
import com.example.demojpa1.customer.model.Customer;
import com.example.demojpa1.dto.CustomerDto;
import com.example.demojpa1.dto.PlanetDto;
import com.example.demojpa1.dto.ReservationDto;
import com.example.demojpa1.dto.SpaceshipDto;
import com.example.demojpa1.planet.model.Planet;
import com.example.demojpa1.reservation.model.Reservation;
import com.example.demojpa1.spaceship.model.Spaceship;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class DtoFactory {

    private static ModelMapper modelMapper = DemoJpa1Application.modelMapper();

    /*
        Customers
     */
    public static CustomerDto fromCustomer(Customer customer) {
        return modelMapper.map(customer, CustomerDto.class);
    }
    public static Customer fromCustomerDto(CustomerDto dto) {
        return modelMapper.map( dto, Customer.class);
    }

    public static List<CustomerDto> fromCustomers(List<Customer> customers) {
        return customers.stream().map(customer -> modelMapper.map(customer, CustomerDto.class))
                .collect(Collectors.toList());
    }

    /*
        Planets
     */
    public static PlanetDto fromPlanet(Planet planet) {
        return modelMapper.map(planet, PlanetDto.class);
    }
    public static Planet fromPlanetDto(PlanetDto dto) {
        return modelMapper.map( dto, Planet.class);
    }

    public static List<PlanetDto> fromPlanets(List<Planet> planets) {
        return planets.stream().map(planet -> modelMapper.map(planet, PlanetDto.class))
                .collect(Collectors.toList());
    }

    /*
        Spaceships
     */
    public static SpaceshipDto fromSpaceship(Spaceship spaceship) {
        return modelMapper.map(spaceship, SpaceshipDto.class);
    }
    public static Spaceship fromSpaceshipDto(SpaceshipDto dto) {
        return modelMapper.map( dto, Spaceship.class);
    }

    public static List<SpaceshipDto> fromSpaceships(List<Spaceship> spaceships) {
        return spaceships.stream().map(spaceship -> modelMapper.map(spaceship, SpaceshipDto.class))
                .collect(Collectors.toList());
    }

    /*
      Reservations
   */
    public static ReservationDto fromReservation(Reservation reservation) {
        return modelMapper.map(reservation, ReservationDto.class);
    }
    public static Reservation fromReservationDto(ReservationDto dto) {
        return modelMapper.map( dto, Reservation.class);
    }

    public static List<ReservationDto> fromReservations(List<Reservation> reservations) {
        return reservations.stream().map(reservation -> modelMapper.map(reservation, ReservationDto.class))
                .collect(Collectors.toList());
    }

}
