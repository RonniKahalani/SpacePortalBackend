package com.example.demojpa1.spaceship.repository;

import com.example.demojpa1.spaceship.model.Spaceship;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SpaceshipRepository extends CrudRepository<Spaceship, Long> {

  List<Spaceship> findByName(String name);

  Optional<Spaceship> findById(long id);

}