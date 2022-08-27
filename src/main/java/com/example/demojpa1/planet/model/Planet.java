package com.example.demojpa1.planet.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Planet JPA entity class.
 *
 * @author Jaron Kahalani
 */
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
    private Double mass;

    @Column(name = "DIAMETER")
    private Double diameter;

    @Column(name = "DENSITY")
    private Integer density;

    @Column(name = "GRAVITY")
    private Double gravity;

    @Column(name = "HOURSPERDAY")
    private Double hoursPerDay;

    @Column(name = "DISTANCEFROMSUN")
    private Double distanceFromSun;

    @Column(name = "MEANTEMP")
    private Integer meanTemp;

    @Column(name = "MOONS")
    private Integer moons;

    @Column(name = "NOTES")
    private String notes;

    @Column(name = "IMAGEURL")
    private String imageUrl;

    @Column(name = "VIDEOURL")
    private String videoUrl;

    @Column(name = "THUMBNAILURL")
    private String thumbnailUrl;

    @ManyToMany(cascade = {
            CascadeType.MERGE
    })
    @JoinTable(name = "planet_planettype",
            joinColumns = @JoinColumn(name = "planet_id"),
            inverseJoinColumns = @JoinColumn(name = "planettype_id")
    )
    private List<PlanetType> types = new ArrayList<>();

    /**
     * Constructs a new planet.
     *
     * @param name
     * @param mass
     * @param diameter
     * @param density
     * @param gravity
     * @param hoursPerDay
     * @param distanceFromSun
     * @param meanTemp
     * @param moons
     * @param notes
     * @param imageUrl
     * @param videoUrl
     * @param thumbnailUrl
     * @param types
     */
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

    public Planet updateFrom(Planet planet, boolean partial) {
        if(!partial || planet.name!=null) {this.name = planet.name;}
        if(!partial || planet.mass!=null) {this.mass = planet.mass;}
        if(!partial || planet.diameter!=null) {this.diameter = planet.diameter;}
        if(!partial || planet.density!=null) {this.density = planet.density;}
        if(!partial || planet.gravity!=null) {this.gravity = planet.gravity;}
        if(!partial || planet.hoursPerDay!=null) {this.hoursPerDay = planet.hoursPerDay;}
        if(!partial || planet.distanceFromSun!=null) {this.distanceFromSun = planet.distanceFromSun;}
        if(!partial || planet.meanTemp!=null) {this.meanTemp = planet.meanTemp;}
        if(!partial || planet.moons!=null) {this.moons = planet.moons;}
        if(!partial || planet.notes!=null) {this.notes = planet.notes;}
        if(!partial || planet.imageUrl!=null) {this.imageUrl = planet.imageUrl;}
        if(!partial || planet.videoUrl!=null) {this.videoUrl = planet.videoUrl;}
        if(!partial || planet.thumbnailUrl!=null) {this.thumbnailUrl = planet.thumbnailUrl;}
        if(!partial || planet.types!=null) {this.types = planet.types;}
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