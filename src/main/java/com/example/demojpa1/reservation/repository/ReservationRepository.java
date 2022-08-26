package com.example.demojpa1.reservation.repository;

import com.example.demojpa1.reservation.model.Reservation;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    //List<Reservation> findByName(String name);

    Optional<Reservation> findById(long id);
}
