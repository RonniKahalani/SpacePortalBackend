package com.example.demojpa1.planet.service;
import com.example.demojpa1.advice.ResourceNotFoundException;
import com.example.demojpa1.customer.model.Customer;
import com.example.demojpa1.planet.model.Planet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlanetService {

    private final CrudRepository<Planet, Long> repository;

    public PlanetService(CrudRepository<Planet, Long> repository) {

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
