package com.example.demojpa1.customer.repository;

import java.util.List;
import java.util.Optional;

import com.example.demojpa1.customer.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

  List<Customer> findByLastName(String lastName);

  Optional<Customer> findById(long id);

}