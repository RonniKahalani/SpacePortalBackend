package com.example.demojpa1.customer.service;

import com.example.demojpa1.advice.ResourceNotFoundException;
import com.example.demojpa1.customer.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
//@EnableMapRepositories
public class CustomerService {

    /**
     * Fetches the {@link com.example.demojpa1.planet.model.Planet Planet}
     */
    private final CrudRepository<Customer, Long> repository;

    public CustomerService(CrudRepository<Customer, Long> repository) {
        this.repository = repository;
    }

    /**
     * Finds all customers.
     *
     * @return all customers
     */
    public Iterable<Customer> findAll() {
        List<Customer> list = new ArrayList<>();
        Iterable<Customer> items = repository.findAll();
        items.forEach(list::add);
        return list;
    }

    public Optional<Customer> find(Long id) throws ResourceNotFoundException {
        Optional<Customer> customer = repository.findById(id);
        return customer;
    }

    public Customer create(Customer customer) {
        return repository.save(customer);
    }

    public Optional<Customer> update(Long id, Customer customer, boolean partial) {
        return repository.findById(id)
                .map(oldItem -> {
                    return repository.save(oldItem.updateFrom(customer, partial));
                });
    }

    public Customer delete(Long id) {
        repository.deleteById(id);
        return null;
    }
}
