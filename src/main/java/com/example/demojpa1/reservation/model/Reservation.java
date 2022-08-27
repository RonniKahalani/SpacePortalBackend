package com.example.demojpa1.reservation.model;

import com.example.demojpa1.customer.model.Customer;
import com.example.demojpa1.planet.model.Planet;
import com.example.demojpa1.spaceship.model.Spaceship;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "RESERVATION")
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "STARTDATE")
    Date startDate;

    @Column(name = "ENDDATE")
    Date endDate;

    @ManyToMany(mappedBy = "reservations")
    List<Customer> customers = new ArrayList<>();

    @ManyToOne
    Planet planet;

    @ManyToOne
    Spaceship spaceship;

    public Reservation(Date startDate, Date endDate, List<Customer> customers, Planet planet, Spaceship spaceship) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.customers = customers;
        this.planet = planet;
        this.spaceship = spaceship;
    }

    public Reservation updateWith(Reservation reservation) {
        this.startDate = reservation.startDate;
        this.endDate = reservation.endDate;
        this.customers = reservation.customers;
        this.planet = reservation.planet;
        this.spaceship = reservation.spaceship;
        return this;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", customers=" + customers +
                ", planet=" + planet +
                ", spaceship=" + spaceship +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(id, that.id) && startDate.equals(that.startDate) && endDate.equals(that.endDate) && Objects.equals(customers, that.customers) && planet.equals(that.planet) && spaceship.equals(that.spaceship);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startDate, endDate, customers, planet, spaceship);
    }
}
