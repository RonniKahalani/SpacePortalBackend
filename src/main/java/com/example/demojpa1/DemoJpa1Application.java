package com.example.demojpa1;

import com.example.demojpa1.customer.model.Customer;
import com.example.demojpa1.customer.repository.CustomerRepository;
import com.example.demojpa1.planet.model.Planet;
import com.example.demojpa1.planet.model.PlanetType;
import com.example.demojpa1.planet.repository.PlanetRepository;
import com.example.demojpa1.planet.repository.PlanetTypeRepository;
import com.example.demojpa1.reservation.model.Reservation;
import com.example.demojpa1.reservation.repository.ReservationRepository;
import com.example.demojpa1.spaceship.model.Spaceship;
import com.example.demojpa1.spaceship.repository.SpaceshipRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootApplication
public class DemoJpa1Application {

    private static final Logger log = LoggerFactory.getLogger(DemoJpa1Application.class);

    public static void main(String[] args) {
        SpringApplication.run(DemoJpa1Application.class, args);
    }

    @Bean
    public CommandLineRunner importData(CustomerRepository customerRepository,
                                  PlanetRepository planetRepository,
                                  PlanetTypeRepository planetTypeRepository,
                                  ReservationRepository reservationRepository,
                                    SpaceshipRepository spaceshipRepository) {
        return (args) -> {

            /**
             *  Save a few customers
             */
            final List<Customer> customers = new ArrayList<>();
            customers.add(new Customer("Jack", "Bauer"));
            customers.add(new Customer("Chloe", "O'Brian"));
            customers.add(new Customer("Kim", "Bauer"));
            customers.add(new Customer("David", "Palmer"));
            customers.add(new Customer("Michelle", "Dessler"));
            customerRepository.saveAll(customers);

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Customer customer : customerRepository.findAll()) {
                log.info(customer.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            final Optional<Customer> customer = customerRepository.findById(1L);
            log.info("Customer found with findById(1L):");
            log.info("--------------------------------");
            log.info(customer.toString());
            log.info("");

            // fetch customers by last name
            log.info("Customer found with findByLastName('Bauer'):");
            log.info("--------------------------------------------");
            customerRepository.findByLastName("Bauer").forEach(bauer -> log.info(bauer.toString()));

            /**
             *  Creates the list of planet types.
             */
            final List<PlanetType> planetTypes = new ArrayList<>();
            planetTypes.add(new PlanetType("Terrestrial"));
            planetTypes.add(new PlanetType("Jovian"));
            planetTypes.add(new PlanetType("Gas"));
            planetTypes.add(new PlanetType("Ice"));
            planetTypes.add(new PlanetType("Dwarf"));
            planetTypeRepository.saveAll(planetTypes);

            /**
             *  Creates the list of planets.
             */
            final List<Planet> planets = new ArrayList<>();
            planets.add(new Planet("Mercury", 0.330, 4879, 5427, 3.7, 4222.6, 57.9, 167, 0, "Mercury is the smallest planet in the Solar System and the closest to the Sun. Its orbit around the Sun takes 87.97 Earth days, the shortest of all the Sun's planets.", "media/planets/mercury.jpg", "https://www.youtube.com/watch?v=0KBjnNuhRHs&ab_channel=NationalGeographic", null, Arrays.asList(planetTypes.get(0))));
            planets.add(new Planet("Venus", 4.87, 12104, 5243, 8.9, 2802, 108.2, 464, 0, "Venus is the second planet from the Sun and is named after the Roman goddess of love and beauty. As the brightest natural object in Earth's night sky after the Moon.", "media/planets/venus.jpg", "https://www.youtube.com/watch?v=BvXa1n9fjow&ab_channel=NationalGeographic", null, Arrays.asList(planetTypes.get(0))));
            planets.add(new Planet("Earth", 5.97, 12756, 5514, 9.8, 24.0, 149.6, 15, 1, "Earth is the third planet from the Sun and the only astronomical object known to harbor life. While large volumes of water can be found throughout the Solar System, only Earth sustains liquid surface water.", "media/planets/earth.jpg", null, null, Arrays.asList(planetTypes.get(0))));
            planets.add(new Planet("Mars", 0.642, 6792, 3933, 3.7, 24.7, 227.9, -65, 2, "Mars is the fourth planet from the Sun and the second-smallest planet in the Solar System, being larger than only Mercury. In the English language, Mars is named for the Roman god of war.", "media/planets/mars.jpg", null, null, Arrays.asList(planetTypes.get(0))));
            planets.add(new Planet("Jupiter", 1898, 142984, 1326, 23.1, 9.9, 778.6, -110, 67, "Jupiter is the fifth planet from the Sun and the largest in the Solar System. It is a gas giant with a mass more than two and a half times that of all the other planets in the Solar System combined.", "media/planets/jupiter.jpg", null, null, Arrays.asList(planetTypes.get(1), planetTypes.get(2))));
            planets.add(new Planet("Saturn", 568, 120536, 687, 9.0, 10.7, 1433.5, -140, 62, "Saturn is the sixth planet from the Sun and the second-largest in the Solar System, after Jupiter. It is a gas giant with an average radius of about nine and a half times that of Earth.", "media/planets/saturn.jpg", null, null, Arrays.asList(planetTypes.get(1), planetTypes.get(2))));
            planets.add(new Planet("Uranus", 68.8, 51118, 1271, 8.7, 17.2, 2872.5, -195, 27, "Uranus is the seventh planet from the Sun. Its name is a reference to the Greek god of the sky, Uranus (Caelus), who, according to Greek mythology, was the great-grandfather of Ares (Mars), grandfather of Zeus (Jupiter) and father of Cronus (Saturn).", "media/planets/uranus.jpg", null, null, Arrays.asList(planetTypes.get(1), planetTypes.get(3))));
            planets.add(new Planet("Neptune", 102, 49528, 1638, 11.0, 16.1, 4495.1, -200, 14, "Neptune is the eighth planet from the Sun and the farthest known solar planet. In the Solar System, it is the fourth-largest planet by diameter, the third-most-massive planet, and the densest giant planet.", "media/planets/neptune.jpg", null, null, Arrays.asList(planetTypes.get(1), planetTypes.get(3))));
            planets.add(new Planet("Pluto", 0.0146, 2370, 2095, 0.7, 0.7, 153.3, -225, 5, "Pluto is a dwarf planet in the Kuiper belt, a ring of bodies beyond the orbit of Neptune. It was the first object discovered in the Kuiper belt and remains the largest known body in that area.", "media/planets/pluto.jpg", null, null, Arrays.asList(planetTypes.get(4))));
            planetRepository.saveAll(planets);

            /**
             *  Creates the list of customers to include in a reservation.
             */

            List<Spaceship> spaceships = new ArrayList<>();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            spaceships.add( new Spaceship("Cube", 25, 100000, 100000,  dateFormat.parse("01-01-1977"), "An acceptable ride, if you have the time.", "media/spaceships/spaceship1.jpg", null, null));
            spaceships.add( new Spaceship("Millennium Falcon", 250, 300000, 5000000, dateFormat.parse("01-01-2015"), "Great ship with lots of room and luxury features.", "media/spaceships/spaceship2.jpg", null, null));
            spaceships.add( new Spaceship("Mining Shuttle", 5, 100000, 10000, dateFormat.parse("01-01-1990"), "Well, good luck, we hope that you get to your destination.", "media/spaceships/spaceship3.jpg", null, null));
            spaceshipRepository.saveAll(spaceships);

             /**
             *  Creates the list of customers to include in a reservation.
             */
            final List<Customer> reservationCustomers = Arrays.asList(customers.get(0), customers.get(1));

            /**
             *  Creates the reservation.
             */
            Reservation reservation = new Reservation(new Date(), new Date(), customers, planets.get(0), spaceships.get(0));
            reservationRepository.save(reservation);

            /**
             *  Set the bidirectional relation on the customer side, relate the reservation to the customers.
             */
            reservationCustomers.forEach(reservationCustomer -> reservationCustomer.getReservations().add(reservation));
            customerRepository.saveAll(reservationCustomers);

            log.info("Data import done.");
        };

    }

}
