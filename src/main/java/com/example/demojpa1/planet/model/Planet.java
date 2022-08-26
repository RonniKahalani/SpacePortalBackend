package com.example.demojpa1.planet.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "PLANET")
@Entity
public class Planet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NAME")

    private String name;

    @Column(name = "MASS")
    private double mass;

    @Column(name = "DIAMETER")
    private double diameter;

    @Column(name = "DENSITY")
    private int density;

    @Column(name = "GRAVITY")
    private double gravity;

    @Column(name = "HOURSPERDAY")
    private double hoursPerDay;

    @Column(name = "DISTANCEFROMSUN")
    private double distanceFromSun;

    @Column(name = "MEANTEMP")
    private int meanTemp;

    @Column(name = "MOONS")
    private int moons;

    @Column(name = "NOTES")
    private String notes;

    @Column(name = "IMAGEURL")
    private String imageUrl;

    @Column(name = "VIDEOURL")
    private String videoUrl;

    @Column(name = "THUMBNAILURL")
    private String thumbnailUrl;

    //@JsonManagedReference
    @ManyToMany(cascade = {
            CascadeType.MERGE
    })
    @JoinTable(name = "planet_planettype",
            joinColumns = @JoinColumn(name = "planet_id"),
            inverseJoinColumns = @JoinColumn(name = "planettype_id")
    )
    private List<PlanetType> types = new ArrayList<>();

    public Planet(String name, double mass, double diameter, int density, double gravity, double hoursPerDay, double distanceFromSun, int meanTemp, int moons, String notes, String imageUrl, String videoUrl, String thumbnailUrl, List<PlanetType> types) {
        this.name = name;
        this.mass = mass;
        this.diameter = diameter;
        this.density = density;
        this.gravity = gravity;
        this.hoursPerDay = hoursPerDay;
        this.distanceFromSun = distanceFromSun;
        this.meanTemp = meanTemp;
        this.moons = moons;
        this.notes = notes;
        this.imageUrl = imageUrl;
        this.videoUrl = videoUrl;
        this.thumbnailUrl = thumbnailUrl;
        this.types = types;
    }

    public Planet updateFrom(Planet planet) {
        this.name = planet.name;
        this.mass = planet.mass;
        this.diameter = planet.diameter;
        this.density = planet.density;
        this.gravity = planet.gravity;
        this.hoursPerDay = planet.hoursPerDay;
        this.distanceFromSun = planet.distanceFromSun;
        this.meanTemp = planet.meanTemp;
        this.moons = planet.moons;
        this.notes = planet.notes;
        this.imageUrl = planet.imageUrl;
        this.videoUrl = planet.videoUrl;
        this.thumbnailUrl = planet.thumbnailUrl;
        this.types = planet.types;
        return this;
    }

    @Override
    public String toString() {
        return "Planet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mass=" + mass +
                ", diameter=" + diameter +
                ", density=" + density +
                ", gravity=" + gravity +
                ", hoursPerDay=" + hoursPerDay +
                ", distanceFromSun=" + distanceFromSun +
                ", meanTemp=" + meanTemp +
                ", moons=" + moons +
                ", notes='" + notes + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                ", types=" + types +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planet planet = (Planet) o;
        return Double.compare(planet.mass, mass) == 0 && Double.compare(planet.diameter, diameter) == 0 && density == planet.density && Double.compare(planet.gravity, gravity) == 0 && Double.compare(planet.hoursPerDay, hoursPerDay) == 0 && Double.compare(planet.distanceFromSun, distanceFromSun) == 0 && meanTemp == planet.meanTemp && moons == planet.moons && Objects.equals(id, planet.id) && name.equals(planet.name) && notes.equals(planet.notes) && imageUrl.equals(planet.imageUrl) && videoUrl.equals(planet.videoUrl) && Objects.equals(thumbnailUrl, planet.thumbnailUrl) && Objects.equals(types, planet.types);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, mass, diameter, density, gravity, hoursPerDay, distanceFromSun, meanTemp, moons, notes, imageUrl, videoUrl, thumbnailUrl, types);
    }
}