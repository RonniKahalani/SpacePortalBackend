package com.example.demojpa1.planet.controller;

import com.example.demojpa1.advice.ResourceNotFoundException;
import com.example.demojpa1.dto.PlanetDto;
import com.example.demojpa1.factory.DtoFactory;
import com.example.demojpa1.planet.model.Planet;
import com.example.demojpa1.planet.service.PlanetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Planet REST API Controller.
 */
@CrossOrigin // Allow all domain origins.
@RestController
@RequestMapping("api/v1/planets")
public class PlanetController {
    /**
     * Service for planets.
     */
    private final PlanetService service;

    /**
     * Constructor with injected repository and service.
     *
     * @param service
     */
    public PlanetController(PlanetService service) {
        this.service = service;
    }

    /**
     * Handles getting/finding all planets.
     *
     * @return customers
     * @see <a href="http://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/GET">HTTP GET</a>
     */
    @GetMapping
    ResponseEntity<List<PlanetDto>> findAll() {
        List<Planet> all = (List<Planet>) service.findAll();
        return ResponseEntity.ok().body(DtoFactory.fromPlanets(all));
    }

    /**
     * Handles getting/finding a planet.
     *
     * @param id
     * @return planet
     * @throws ResourceNotFoundException
     * @see <a href="http://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/GET">HTTP GET</a>
     */
    @GetMapping("/{id}")
    public ResponseEntity<PlanetDto> find(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Optional<Planet> item = Optional.of(service.find(id)
                .orElseThrow(() -> new ResourceNotFoundException("Planet %d not found.".formatted(id))));
        return ResponseEntity.ok().body(DtoFactory.fromPlanet(item.get()));
    }

    /**
     * Handles posting/creating a planet.
     *
     * @param dto
     * @return newly created planet
     * @see <a href="http://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/POST">HTTP POST</a>
     */
    @PostMapping()
    public ResponseEntity<PlanetDto> create(@Valid @RequestBody PlanetDto dto) {
        Planet item = service.create(DtoFactory.fromPlanetDto(dto));
        return ResponseEntity.ok().body(DtoFactory.fromPlanet(item));
    }

    /**
     * Handles putting a customer.
     *
     * @param id
     * @param dto
     * @return updated planet
     * @see <a href="http://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/PUT">HTTP PUT</a>
     */
    @PutMapping("/{id}")
    public ResponseEntity<PlanetDto> put(@PathVariable("id") Long id, @Valid @RequestBody PlanetDto dto) {
        return ResponseEntity.ok().body(update(id, dto, false));
    }

    /**
     * Handles patching a planet.
     *
     * @param id
     * @param dto
     * @return updated planet
     * @see <a href="http://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/PATCH">HTTP PATCH</a>
     */
    @PatchMapping("/{id}")
    public ResponseEntity<PlanetDto> patch(@PathVariable("id") Long id, @Valid @RequestBody PlanetDto dto) {
        return ResponseEntity.ok().body(update(id, dto, true));
    }

    /**
     * Handles deleting a planet.
     *
     * @param id
     * @return a null value planet
     * @see <a href="http://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/DELETE">HTTP DELETE</a>
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Planet> delete(@PathVariable("id") Long id) {
        service.find(id).orElseThrow(() -> new ResourceNotFoundException("Planet %d not found.".formatted(id)));

        Planet delete = service.delete(id);
        return ResponseEntity.ok().body(delete);
    }

    /**
     * Updates a spaceship Dto from another spaceship Dto.
     *
     * @param id
     * @param dto
     * @return the updated spaceship Dto
     */
    private PlanetDto update(Long id, PlanetDto dto, boolean partial) {
        Optional<Planet> item = service.update(id, DtoFactory.fromPlanetDto(dto), partial);

        if (!item.isPresent()) {
            throw new ResourceNotFoundException("Planet %d not found".formatted(id));
        }

        return DtoFactory.fromPlanet(item.get());
    }
}