package com.example.demojpa1.spaceship.service;

import com.example.demojpa1.advice.ResourceNotFoundException;
import com.example.demojpa1.customer.model.Customer;
import com.example.demojpa1.spaceship.model.Spaceship;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SpaceshipService {

    private final CrudRepository<Spaceship, Long> repository;

    public SpaceshipService(CrudRepository<Spaceship, Long> repository) {
        this.repository = repository;
    }

    public Iterable<Spaceship> findAll() {
        List<Spaceship> list = new ArrayList<>();
        Iterable<Spaceship> items = repository.findAll();
        items.forEach(list::add);
        return list;
    }

    public Optional<Spaceship> find(Long id) {
        return repository.findById(id);
    }

    public Spaceship create(Spaceship spaceship) {
        return repository.save(spaceship);
    }

    public Optional<Spaceship> update(Long id, Spaceship spaceship) {
        return repository.findById(id)
                .map(oldItem -> {
                    return repository.save( oldItem.updateWith(spaceship));
                });
    }

    public Spaceship delete(Long id) {
        repository.deleteById(id);
        return null;
    }
}
