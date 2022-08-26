package com.example.demojpa1.reservation.controller;

import com.example.demojpa1.advice.ResourceNotFoundException;
import com.example.demojpa1.customer.model.Customer;
import com.example.demojpa1.dto.ReservationDto;
import com.example.demojpa1.factory.DtoFactory;
import com.example.demojpa1.planet.model.Planet;
import com.example.demojpa1.reservation.model.Reservation;
import com.example.demojpa1.reservation.repository.ReservationRepository;
import com.example.demojpa1.reservation.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin // Allow all domain origins.
@RestController
@RequestMapping("api/v1/reservations")
public class ReservationController {

    private final ReservationRepository repository;
    private final ReservationService service;

    /**
     * Controller with constructor injection.
     *
     * @param repository
     * @param service
     */
    public ReservationController(ReservationRepository repository, ReservationService service) {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping
    ResponseEntity<List<ReservationDto>> findAll() {
        List<Reservation> all = (List<Reservation>) repository.findAll();
        return ResponseEntity.ok().body(DtoFactory.fromReservations(all));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDto> find(@PathVariable("id") Long id) {
        Optional<Reservation> item = Optional.of(service.find(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation %d not found.".formatted(id))));
        return ResponseEntity.ok().body(DtoFactory.fromReservation(item.get()));
    }

    @PostMapping
    public ResponseEntity<ReservationDto> create(@Valid @RequestBody ReservationDto dto) {
        Reservation item = service.create(DtoFactory.fromReservationDto(dto));
        return ResponseEntity.ok().body(DtoFactory.fromReservation(item));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ReservationDto> put(@PathVariable("id") Long id, @Valid @RequestBody ReservationDto dto) {
        return ResponseEntity.ok().body(update(id, dto));
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<ReservationDto> patch(@PathVariable("id") Long id, @Valid @RequestBody ReservationDto dto) {
        return ResponseEntity.ok().body(update(id, dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Reservation> delete(@PathVariable("id") Long id) {
        service.find(id).orElseThrow(() -> new ResourceNotFoundException("Reservation %d not found.".formatted(id)));

        Reservation delete = service.delete(id);
        return ResponseEntity.ok().body(delete);
    }

    @GetMapping("/{id}/planet")
    public ResponseEntity<Planet> findPlanet(@PathVariable("id") Long id) {
        Optional<Reservation> item = service.find(id);
        return ResponseEntity.of(Optional.of(item.get().getPlanet()));
    }

    @GetMapping("/{id}/customers")
    public ResponseEntity<Iterable<Customer>> findCustomers(@PathVariable("id") Long id) {
        Optional<Reservation> item = service.find(id);
        return ResponseEntity.of(Optional.of(item.get().getCustomers()));
    }

    private ReservationDto update(Long id, ReservationDto dto) {
        Optional<Reservation> item = service.update(id, DtoFactory.fromReservationDto(dto));

        if(!item.isPresent()) {
            throw new ResourceNotFoundException("Reservation %d not found".formatted(id));
        }

        return DtoFactory.fromReservation(item.get());
    }
}