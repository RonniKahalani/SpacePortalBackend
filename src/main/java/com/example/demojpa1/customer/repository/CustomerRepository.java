package com.example.demojpa1.customer.repository;

import java.util.List;
import java.util.Optional;

import com.example.demojpa1.customer.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

  List<Customer> findByLastName(String lastName);
  Optional<Customer> findById(long id);



  List<Customer> findAllByFirstNameIsContaining(String value);

  @Query("SELECT c FROM Customer c ORDER BY c.firstName")
  List<Customer> findAllOrderByFirstName();

}