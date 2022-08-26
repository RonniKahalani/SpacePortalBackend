package com.example.demojpa1.planet.repository;

import com.example.demojpa1.planet.model.Planet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * Used to access data from {@link Planet} the planet database table.
 *
 * @author Jaron Kahalani
 */
public interface PlanetRepository extends CrudRepository<Planet, Long> {
    /**
     * Finds planets by name.
     *
     * @param name
     * @return
     */
    List<Planet> findByName(String name);

    /**
     * Finds planets by id.
     *
     * @param id
     * @return
     */
    Optional<Planet> findById(long id);
}
