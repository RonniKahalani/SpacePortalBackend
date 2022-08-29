package com.example.demojpa1.customer.controller;

import com.example.demojpa1.advice.ResourceNotFoundException;
import com.example.demojpa1.customer.model.Customer;
import com.example.demojpa1.customer.service.CustomerService;
import com.example.demojpa1.dto.CustomerDto;
import com.example.demojpa1.factory.DtoFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Customer REST API Controller.
 */
@CrossOrigin // Allow all domain origins.
@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    /**
     * Service for customers.
     */
    private final CustomerService service;

    /**
     * Constructor, with injected repository and service references.
     *
     * @param service
     */
    public CustomerController(CustomerService service) {
        this.service = service;
    }

    /**
     * Handles getting/finding all customers.
     *
     * @return customers
     * @see <a href="http://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/GET">HTTP GET</a>
     */
    @GetMapping
    ResponseEntity<List<CustomerDto>> findAll() {
        List<Customer> all = (List<Customer>) service.findAll();
        return ResponseEntity.ok().body(DtoFactory.fromCustomers(all));
    }

    /**
     * Handles getting/finding a customer.
     *
     * @param id
     * @return customer
     * @throws ResourceNotFoundException
     * @see <a href="http://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/GET">HTTP GET</a>
     */
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> find(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Optional<Customer> item = Optional.of(service.find(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer %d not found.".formatted(id))));
        return ResponseEntity.ok().body(DtoFactory.fromCustomer(item.get()));
    }

    /**
     * Handles posting/creating a customer.
     *
     * @param dto
     * @return newly created customer
     * @see <a href="http://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/POST">HTTP POST</a>
     */
    @PostMapping
    public ResponseEntity<CustomerDto> create(@Valid @RequestBody CustomerDto dto) {
        Customer item = service.create(DtoFactory.fromCustomerDto(dto));
        return ResponseEntity.ok().body(DtoFactory.fromCustomer(item));
    }

    /**
     * Handles putting a customer.
     *
     * @param id
     * @param dto
     * @return updated customer
     * @see <a href="http://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/PUT">HTTP PUT</a>
     */
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> put(@PathVariable("id") Long id, @Valid @RequestBody CustomerDto dto) {
        return ResponseEntity.ok().body(update(id, dto, false));
    }

    /**
     * Handles patching a customer.
     *
     * @param id
     * @param dto
     * @return updated customer
     * @see <a href="http://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/PATCH">HTTP PATCH</a>
     */
    @PatchMapping("/{id}")
    public ResponseEntity<CustomerDto> patch(@PathVariable("id") Long id, @Valid @RequestBody CustomerDto dto) {
        return ResponseEntity.ok().body(update(id, dto, true));
    }

    /**
     * Handles deleting a customer.
     *
     * @param id
     * @return a null value customer
     * @see <a href="http://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/DELETE">HTTP DELETE</a>
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> delete(@PathVariable("id") Long id) {
        service.find(id).orElseThrow(() -> new ResourceNotFoundException("Customer %d not found.".formatted(id)));

        Customer delete = service.delete(id);
        return ResponseEntity.ok().body(delete);
    }

    /**
     * Updates a customer Dto from another customer Dto.
     *
     * @param id
     * @param dto
     * @return the updated customer Dto
     */
    private CustomerDto update(Long id, CustomerDto dto, boolean partial) {
        Optional<Customer> item = Optional.ofNullable(service.update(id, DtoFactory.fromCustomerDto(dto), partial).orElseThrow(() -> {
            throw new ResourceNotFoundException("Customer %d not found".formatted(id));
        }));
        return DtoFactory.fromCustomer(item.get());
    }
}
