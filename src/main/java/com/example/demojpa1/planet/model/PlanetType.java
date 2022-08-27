package com.example.demojpa1.planet.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor
@Table(name = "PLANETTYPE")
@Entity
public class PlanetType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NAME")
    private String name;

    @ManyToMany(mappedBy = "types")
    private List<Planet> planets = new ArrayList<>();

    public PlanetType(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlanetType planetType = (PlanetType) o;
        return Objects.equals(name, planetType.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}