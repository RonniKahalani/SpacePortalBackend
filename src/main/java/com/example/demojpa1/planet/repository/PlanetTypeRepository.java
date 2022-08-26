package com.example.demojpa1.planet.repository;

import com.example.demojpa1.planet.model.PlanetType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PlanetTypeRepository extends CrudRepository<PlanetType, Long> {
    List<PlanetType> findByName(String name);

    Optional<PlanetType> findById(long id);
}
