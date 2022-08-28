package com.example.demojpa1.reservation.controller;

import com.example.demojpa1.advice.ResourceNotFoundException;
import com.example.demojpa1.dto.CustomerDto;
import com.example.demojpa1.dto.PlanetDto;
import com.example.demojpa1.dto.ReservationDto;
import com.example.demojpa1.dto.SpaceshipDto;
import com.example.demojpa1.factory.DtoFactory;
import com.example.demojpa1.reservation.model.Reservation;
import com.example.demojpa1.reservation.repository.ReservationRepository;
import com.example.demojpa1.reservation.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Reservation REST API Controller.
 */
@CrossOrigin // Allow all domain origins.
@RestController
@RequestMapping("api/v1/reservations")
public class ReservationController {
    /**
     * Repository for reservations.
     */
    private final ReservationRepository repository;
    /**
     * Service for reservations.
     */
    private final ReservationService service;

    /**
     * Constructor with injected repository and service.
     *
     * @param repository
     * @param service
     */
    public ReservationController(ReservationRepository repository, ReservationService service) {
        this.repository = repository;
        this.service = service;
    }

    /**
     * Handles getting/finding all reservations.
     *
     * @return reservations
     * @see <a href="http://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/GET">HTTP GET</a>
     */
    @GetMapping
    ResponseEntity<List<ReservationDto>> findAll() {
        List<Reservation> all = (List<Reservation>) repository.findAll();
        return ResponseEntity.ok().body(DtoFactory.fromReservations(all));
    }

    /**
     * Handles getting/finding a reservation.
     *
     * @param id
     * @return reservation
     * @throws ResourceNotFoundException
     * @see <a href="http://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/GET">HTTP GET</a>
     */
    @GetMapping("/{id}")
    public ResponseEntity<ReservationDto> find(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Optional<Reservation> item = Optional.of(service.find(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation %d not found.".formatted(id))));
        return ResponseEntity.ok().body(DtoFactory.fromReservation(item.get()));
    }

    /**
     * Handles posting/creating a reservation.
     *
     * @param dto
     * @return newly created reservation
     * @see <a href="http://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/POST">HTTP POST</a>
     */
    @PostMapping
    public ResponseEntity<ReservationDto> create(@Valid @RequestBody ReservationDto dto) {
        Reservation item = service.create(DtoFactory.fromReservationDto(dto));
        return ResponseEntity.ok().body(DtoFactory.fromReservation(item));
    }

    /**
     * Handles putting a reservation.
     *
     * @param id
     * @param dto
     * @return updated reservation
     * @see <a href="http://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/PUT">HTTP PUT</a>
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<ReservationDto> put(@PathVariable("id") Long id, @Valid @RequestBody ReservationDto dto) {
        return ResponseEntity.ok().body(update(id, dto));
    }

    /**
     * Handles patching a reservation.
     *
     * @param id
     * @param dto
     * @return updated reservation
     * @see <a href="http://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/PATCH">HTTP PATCH</a>
     */
    @PatchMapping(value = "/{id}")
    public ResponseEntity<ReservationDto> patch(@PathVariable("id") Long id, @Valid @RequestBody ReservationDto dto) {
        return ResponseEntity.ok().body(update(id, dto));
    }

    /**
     * Handles deleting a reservation.
     *
     * @param id
     * @return a null value reservation
     * @see <a href="http://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/DELETE">HTTP DELETE</a>
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Reservation> delete(@PathVariable("id") Long id) {
        service.find(id).orElseThrow(() -> new ResourceNotFoundException("Reservation %d not found.".formatted(id)));

        Reservation delete = service.delete(id);
        return ResponseEntity.ok().body(delete);
    }

    /**
     * Handles listing the reservation planet.
     *
     * @param id
     * @return reservation planet
     */
    @GetMapping("/{id}/planet")
    public ResponseEntity<PlanetDto> findPlanet(@PathVariable("id") Long id) {
        Optional<Reservation> item = service.find(id);
        return ResponseEntity.of(Optional.of(DtoFactory.fromPlanet(item.get().getPlanet())));
    }
    
    /**
     * Handles listing the reservation spaceship.
     *
     * @param id
     * @return reservation spaceship
     */
    @GetMapping("/{id}/spaceship")
    public ResponseEntity<SpaceshipDto> findSpaceship(@PathVariable("id") Long id) {
        Optional<Reservation> item = service.find(id);
        return ResponseEntity.of(Optional.of(DtoFactory.fromSpaceship(item.get().getSpaceship())));
    }

    /**
     * Handles listing the reservation customers.
     *
     * @param id
     * @return reservation customers
     */
    @GetMapping("/{id}/customers")
    public ResponseEntity<Iterable<CustomerDto>> findCustomers(@PathVariable("id") Long id) {
        Optional<Reservation> item = service.find(id);
        return ResponseEntity.of(Optional.of(DtoFactory.fromCustomers(item.get().getCustomers())));
    }

    /**
     * Updates a reservation Dto from another reservation Dto.
     *
     * @param id
     * @param dto
     * @return the updated reservation Dto
     */
    private ReservationDto update(Long id, ReservationDto dto) {
        Optional<Reservation> item = service.update(id, DtoFactory.fromReservationDto(dto));

        if (!item.isPresent()) {
            throw new ResourceNotFoundException("Reservation %d not found".formatted(id));
        }

        return DtoFactory.fromReservation(item.get());
    }
}