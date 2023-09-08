package com.example.demojpa1.planet.service;

import com.example.demojpa1.planet.model.Planet;
import com.example.demojpa1.planet.repository.PlanetRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * kjdhfkjdshfkdsjfhkdsjfhdskfjh
 */
@Service
public class PlanetService {

    private final PlanetRepository repository;

    public PlanetService(PlanetRepository repository) {

        this.repository = repository;
    }

    public Iterable<Planet> findAll() {
        List<Planet> list = new ArrayList<>();
        Iterable<Planet> items = repository.findAll();
        items.forEach(list::add);
        return list;
    }

    public Optional<Planet> find(Long id) {
        return repository.findById(id);
    }

    public Planet create(Planet planet) {
        return repository.save(planet);
    }

    public Optional<Planet> update( Long id, Planet planet, boolean partial) {
        return repository.findById(id)
                .map(oldItem -> {
                    return repository.save(oldItem.updateFrom(planet, partial));
                });
    }

    public Planet delete(Long id) {
        repository.deleteById(id);
        return null;
    }
}
