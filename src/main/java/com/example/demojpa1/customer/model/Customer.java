package com.example.demojpa1.customer.model;

import com.example.demojpa1.reservation.model.Reservation;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Customer JPA entity class.
 *
 * @author Jaron Kahalani
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "CUSTOMER")
public class Customer {

    /**
     * Autogenerated entity id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Customer first name.
     */
    @Column(name = "FIRSTNAME")
    private String firstName;

    /**
     * Customer last name.
     */
    @Column(name = "LASTNAME")
    private String lastName;

    /**
     * List of reservations held by the customer.
     */
    @ManyToMany(cascade = {
            CascadeType.MERGE
    })
    @JoinTable(name = "customer_reservation",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "reservation_id")
    )
    private List<Reservation> reservations = new ArrayList<>();

    /**
     * Constructs a new customer.
     *
     * @param firstName
     * @param lastName
     */
    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Constructs a new customer.
     *
     * @param firstName
     * @param lastName
     * @param reservations
     */
    public Customer(String firstName, String lastName, List<Reservation> reservations) {
        this(firstName, lastName);
        this.reservations = reservations;
    }

    public Customer updateFrom(Customer customer, boolean partial) {
        if(!partial || customer.firstName!=null) {this.firstName = customer.firstName;}
        if(!partial || customer.lastName!=null) {this.lastName = customer.lastName;}
        if(!partial || customer.reservations!=null) {this.reservations = customer.reservations;}
        return this;
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    /**
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) && firstName.equals(customer.firstName) && lastName.equals(customer.lastName) && Objects.equals(reservations, customer.reservations);
    }

    /**
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, reservations);
    }
}

