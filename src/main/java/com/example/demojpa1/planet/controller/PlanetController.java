package com.example.demojpa1.planet.controller;

import com.example.demojpa1.advice.ResourceNotFoundException;
import com.example.demojpa1.dto.PlanetDto;
import com.example.demojpa1.factory.DtoFactory;
import com.example.demojpa1.planet.model.Planet;
import com.example.demojpa1.planet.repository.PlanetRepository;
import com.example.demojpa1.planet.service.PlanetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin // Allow all domain origins.
@RestController
@RequestMapping("api/v1/planets")
public class PlanetController {
    private final PlanetRepository repository;
    private final PlanetService service;

    public PlanetController( PlanetRepository repository, PlanetService service) {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping
    ResponseEntity<List<PlanetDto>> findAll() {
        List<Planet> all = (List<Planet>) repository.findAll();
        return ResponseEntity.ok().body(DtoFactory.fromPlanets(all));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanetDto> find(@PathVariable("id") Long id) {
        Optional<Planet> item = Optional.of(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Planet %d not found.".formatted(id))));
        return ResponseEntity.ok().body( DtoFactory.fromPlanet(item.get()));
    }

    @PostMapping()
    public ResponseEntity<PlanetDto> create(@Valid @RequestBody PlanetDto dto) {
        Planet item = service.create( DtoFactory.fromPlanetDto(dto));
        return ResponseEntity.ok().body( DtoFactory.fromPlanet(item));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanetDto> put(@PathVariable("id") Long id, @Valid @RequestBody PlanetDto dto) {
        return ResponseEntity.ok().body(update(id, dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PlanetDto> patch(@PathVariable("id") Long id, @Valid @RequestBody PlanetDto dto) {
        return ResponseEntity.ok().body(update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Planet> delete(@PathVariable("id") Long id) {
        service.find(id).orElseThrow(() -> new ResourceNotFoundException("Planet %d not found.".formatted(id)));

        Planet delete = service.delete(id);
        return ResponseEntity.ok().body(delete);
    }

    private PlanetDto update(Long id, PlanetDto dto) {
        Optional<Planet> item = service.update(id, DtoFactory.fromPlanetDto(dto));

        if(!item.isPresent()) {
            throw new ResourceNotFoundException("Planet %d not found".formatted(id));
        }

        return DtoFactory.fromPlanet(item.get());
    }
}