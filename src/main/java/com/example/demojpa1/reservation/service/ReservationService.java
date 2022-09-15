package com.example.demojpa1.reservation.service;

import com.example.demojpa1.reservation.model.Reservation;
import com.example.demojpa1.reservation.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository repository;

    public ReservationService(ReservationRepository repository) {
        this.repository = repository;
    }

    public Iterable<Reservation> findAll() {
        List<Reservation> list = new ArrayList<>();
        Iterable<Reservation> items = repository.findAll();
        items.forEach(list::add);
        return list;
    }

    public Optional<Reservation> find(Long id) {
        return repository.findById(id);
    }

    public Reservation create(Reservation reservation) {
        return repository.save(reservation);
    }

    public Optional<Reservation> update(Long id, Reservation reservation) {
        return repository.findById(id)
                .map(oldItem -> {
                    return repository.save(oldItem.updateWith(reservation));
                });
    }

    public Reservation delete(Long id) {
        repository.deleteById(id);
        return null;
    }
}
