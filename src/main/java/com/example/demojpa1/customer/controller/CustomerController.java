package com.example.demojpa1.customer.controller;

import com.example.demojpa1.advice.ResourceNotFoundException;
import com.example.demojpa1.customer.model.Customer;
import com.example.demojpa1.customer.repository.CustomerRepository;
import com.example.demojpa1.customer.service.CustomerService;
import com.example.demojpa1.dto.CustomerDto;
import com.example.demojpa1.factory.DtoFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Customer API Controller.
 */
@CrossOrigin // Allow all domain origins.
@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    /**
     * Repository for customers.
     */
    private final CustomerRepository repository;
    /**
     * Service for customers.
     */
    private final CustomerService service;

    /**
     * Constructor, with injected repository and service references.
     *
     * @param repository
     * @param service
     */
    public CustomerController(CustomerRepository repository, CustomerService service) {
        this.repository = repository;
        this.service = service;
    }

    /**
     * Returns all customers.
     *
     * @return customers
     */
    @GetMapping
    ResponseEntity<List<CustomerDto>> findAll() {
        List<Customer> all = (List<Customer>) repository.findAll();
        return ResponseEntity.ok().body(DtoFactory.fromCustomers(all));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> find(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Optional<Customer> item = Optional.of(service.find(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer %d not found.".formatted(id))));
        return ResponseEntity.ok().body(DtoFactory.fromCustomer(item.get()));
    }

    @PostMapping
    public ResponseEntity<CustomerDto> create(@Valid @RequestBody CustomerDto dto) {
        Customer item = service.create(DtoFactory.fromCustomerDto(dto));
        return ResponseEntity.ok().body(DtoFactory.fromCustomer(item));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> put(@PathVariable("id") Long id, @Valid @RequestBody CustomerDto dto) {
        return ResponseEntity.ok().body(update(id, dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerDto> patch(@PathVariable("id") Long id, @Valid @RequestBody CustomerDto dto) {
        return ResponseEntity.ok().body(update(id, dto));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> delete(@PathVariable("id") Long id) {
        service.find(id).orElseThrow(() -> new ResourceNotFoundException("Customer %d not found.".formatted(id)));

        Customer delete = service.delete(id);
        return ResponseEntity.ok().body(delete);
    }

    private CustomerDto update(Long id, CustomerDto dto) {
        Optional<Customer> item = service.update(id, DtoFactory.fromCustomerDto(dto));

        if(!item.isPresent()) {
            throw new ResourceNotFoundException("Customer %d not found".formatted(id));
        }

        return DtoFactory.fromCustomer(item.get());
    }
}
