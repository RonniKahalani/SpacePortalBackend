package com.example.demojpa1.spaceship.controller;

import com.example.demojpa1.advice.ResourceNotFoundException;
import com.example.demojpa1.dto.SpaceshipDto;
import com.example.demojpa1.factory.DtoFactory;
import com.example.demojpa1.spaceship.model.Spaceship;
import com.example.demojpa1.spaceship.repository.SpaceshipRepository;
import com.example.demojpa1.spaceship.service.SpaceshipService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin // Allow all domain origins.
@RestController
@RequestMapping("api/v1/spaceships")
public class SpaceshipController {
    private final SpaceshipRepository repository;
    private final SpaceshipService service;

    public SpaceshipController(SpaceshipRepository repository, SpaceshipService service) {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping
    ResponseEntity<List<SpaceshipDto>> findAll() {
        List<Spaceship> all = (List<Spaceship>) repository.findAll();
        return ResponseEntity.ok().body(DtoFactory.fromSpaceships(all));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpaceshipDto> find(@PathVariable("id") Long id) {
        Optional<Spaceship> item = Optional.of(service.find(id)
                .orElseThrow(() -> new ResourceNotFoundException("Spaceship %d not found.".formatted(id))));
        return ResponseEntity.ok().body( DtoFactory.fromSpaceship(item.get()));
    }

    @PostMapping
    public ResponseEntity<SpaceshipDto> create(@Valid @RequestBody SpaceshipDto dto) {
        Spaceship item = service.create( DtoFactory.fromSpaceshipDto(dto));
        return ResponseEntity.ok().body( DtoFactory.fromSpaceship(item));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpaceshipDto> put(@PathVariable("id") Long id, @Valid @RequestBody SpaceshipDto dto) {
        return ResponseEntity.ok().body(update(id, dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SpaceshipDto> patch(@PathVariable("id") Long id, @Valid @RequestBody SpaceshipDto dto) {
        return ResponseEntity.ok().body(update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Spaceship> delete(@PathVariable("id") Long id) {
        service.find(id).orElseThrow(() -> new ResourceNotFoundException("Spaceship %d not found.".formatted(id)));

        Spaceship delete = service.delete(id);
        return ResponseEntity.ok().body(delete);
    }

    private SpaceshipDto update(Long id, SpaceshipDto dto) {
        Optional<Spaceship> item = service.update(id, DtoFactory.fromSpaceshipDto(dto));

        if(!item.isPresent()) {
            throw new ResourceNotFoundException("Spaceship %d not found".formatted(id));
        }

        return DtoFactory.fromSpaceship(item.get());
    }
}
