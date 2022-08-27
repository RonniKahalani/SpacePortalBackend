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

/**
 * Spaceship REST API Controller.
 */
@CrossOrigin // Allow all domain origins.
@RestController
@RequestMapping("api/v1/spaceships")
public class SpaceshipController {
    /**
     * Repository for spaceships.
     */
    private final SpaceshipRepository repository;
    /**
     * Service for spaceship.
     */
    private final SpaceshipService service;

    /**
     * Constructor with injected repository and service.
     *
     * @param repository
     * @param service
     */
    public SpaceshipController(SpaceshipRepository repository, SpaceshipService service) {
        this.repository = repository;
        this.service = service;
    }

    /**
     * Handles getting/finding all spaceships.
     *
     * @return spaceships
     * @see <a href="http://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/GET">HTTP GET</a>
     */
    @GetMapping
    ResponseEntity<List<SpaceshipDto>> findAll() {
        List<Spaceship> all = (List<Spaceship>) repository.findAll();
        return ResponseEntity.ok().body(DtoFactory.fromSpaceships(all));
    }

    /**
     * Handles getting/finding a spaceship.
     *
     * @param id
     * @return spaceship
     * @throws ResourceNotFoundException
     * @see <a href="http://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/GET">HTTP GET</a>
     */
    @GetMapping("/{id}")
    public ResponseEntity<SpaceshipDto> find(@PathVariable("id") Long id) {
        Optional<Spaceship> item = Optional.of(service.find(id)
                .orElseThrow(() -> new ResourceNotFoundException("Spaceship %d not found.".formatted(id))));
        return ResponseEntity.ok().body(DtoFactory.fromSpaceship(item.get()));
    }

    /**
     * Handles posting/creating a spaceship.
     *
     * @param dto
     * @return newly created spaceship
     * @see <a href="http://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/POST">HTTP POST</a>
     */
    @PostMapping
    public ResponseEntity<SpaceshipDto> create(@Valid @RequestBody SpaceshipDto dto) {
        Spaceship item = service.create(DtoFactory.fromSpaceshipDto(dto));
        return ResponseEntity.ok().body(DtoFactory.fromSpaceship(item));
    }

    /**
     * Handles putting a spaceship.
     *
     * @param id
     * @param dto
     * @return updated spaceship
     * @see <a href="http://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/PUT">HTTP PUT</a>
     */
    @PutMapping("/{id}")
    public ResponseEntity<SpaceshipDto> put(@PathVariable("id") Long id, @Valid @RequestBody SpaceshipDto dto) {
        return ResponseEntity.ok().body(update(id, dto, false));
    }

    /**
     * Handles patching a spaceship.
     *
     * @param id
     * @param dto
     * @return updated spaceship
     * @see <a href="http://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/PATCH">HTTP PATCH</a>
     */
    @PatchMapping("/{id}")
    public ResponseEntity<SpaceshipDto> patch(@PathVariable("id") Long id, @Valid @RequestBody SpaceshipDto dto) {
        return ResponseEntity.ok().body(update(id, dto, true));
    }

    /**
     * Handles deleting a spaceship.
     *
     * @param id
     * @return a null value spaceship
     * @see <a href="http://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/DELETE">HTTP DELETE</a>
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Spaceship> delete(@PathVariable("id") Long id) {
        service.find(id).orElseThrow(() -> new ResourceNotFoundException("Spaceship %d not found.".formatted(id)));

        Spaceship delete = service.delete(id);
        return ResponseEntity.ok().body(delete);
    }

    /**
     * Updates a spaceship Dto from another spaceship Dto.
     *
     * @param id
     * @param dto
     * @return the updated spaceship Dto
     */
    private SpaceshipDto update(Long id, SpaceshipDto dto, boolean partial) {
        Optional<Spaceship> item = service.update(id, DtoFactory.fromSpaceshipDto(dto), partial);

        if (!item.isPresent()) {
            throw new ResourceNotFoundException("Spaceship %d not found".formatted(id));
        }

        return DtoFactory.fromSpaceship(item.get());
    }
}
