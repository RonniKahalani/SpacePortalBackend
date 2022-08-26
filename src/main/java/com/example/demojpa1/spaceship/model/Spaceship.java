package com.example.demojpa1.spaceship.model;

import com.example.demojpa1.reservation.model.Reservation;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "SPACESHIP")
@Entity
public class Spaceship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "MAXPASSENGERS")
    private int maxPassengers;

    @Column(name = "MAXSPEED")
    private int maxSpeed;

    @Column(name = "MAXLOAD")
    private int maxLoad;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @Column(name = "WEIGHT")
    private Date buildDate;

    @Column(name = "NOTES")
    private String notes;

    @Column(name = "IMAGEURL")
    private String imageUrl;

    @Column(name = "VIDEOURL")
    private String videoUrl;

    @Column(name = "THUMBNAILURL")
    private String thumbnailUrl;

    //@JsonBackReference
    @ManyToMany(cascade = {
            CascadeType.MERGE
    })
    @JoinTable(name = "spaceship_reservation",
            joinColumns = @JoinColumn(name = "spaceship_id"),
            inverseJoinColumns = @JoinColumn(name = "reservation_id")
    )
    private List<Reservation> reservations = new ArrayList<>();

    public Spaceship(Spaceship spaceship) {
        this.name = spaceship.name;
        this.maxPassengers = spaceship.maxPassengers;
        this.maxSpeed = spaceship.maxSpeed;
        this.maxLoad = spaceship.maxLoad;
        this.buildDate = spaceship.buildDate;
        this.notes = spaceship.notes;
        this.imageUrl = spaceship.imageUrl;
        this.videoUrl = spaceship.videoUrl;
        this.thumbnailUrl = spaceship.thumbnailUrl;
        this.reservations = spaceship.reservations;
    }

    public Spaceship(String name, int maxPassengers, int maxSpeed, int maxLoad, Date buildDate, String notes, String imageUrl, String videoUrl, String thumbnailUrl, List<Reservation> reservations)  {
        this(name, maxPassengers, maxSpeed, maxLoad, buildDate, notes, imageUrl, videoUrl, thumbnailUrl);
        this.reservations = reservations;
    }
    public Spaceship(String name, int maxPassengers, int maxSpeed, int maxLoad, Date buildDate, String notes, String imageUrl, String videoUrl, String thumbnailUrl)  {
        this.name = name;
        this.maxPassengers = maxPassengers;
        this.maxSpeed = maxSpeed;
        this.maxLoad = maxLoad;
        this.buildDate = buildDate;
        this.notes = notes;
        this.imageUrl = imageUrl;
        this.videoUrl = videoUrl;
        this.thumbnailUrl = thumbnailUrl;
    }

    public Spaceship(Long id, String name, int maxPassengers, int maxSpeed, int maxLoad, Date buildDate, String notes, String imageUrl, String videoUrl, String thumbnailUrl, List<Reservation> reservations) {
        this(name, maxPassengers, maxSpeed, maxLoad, buildDate, notes, imageUrl, videoUrl, thumbnailUrl);
        this.id = id;
        this.reservations = reservations;
    }

    public Spaceship updateWith(Spaceship spaceship) {
        return new Spaceship(
                spaceship.name,
                spaceship.maxPassengers,
                spaceship.maxSpeed,
                spaceship.maxLoad,
                spaceship.buildDate,
                spaceship.notes,
                spaceship.imageUrl,
                spaceship.videoUrl,
                spaceship.thumbnailUrl,
                spaceship.reservations);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spaceship spaceship = (Spaceship) o;
        return maxPassengers == spaceship.maxPassengers && maxSpeed == spaceship.maxSpeed && maxLoad == spaceship.maxLoad && Objects.equals(id, spaceship.id) && name.equals(spaceship.name) && buildDate.equals(spaceship.buildDate) && notes.equals(spaceship.notes) && imageUrl.equals(spaceship.imageUrl) && Objects.equals(videoUrl, spaceship.videoUrl) && Objects.equals(thumbnailUrl, spaceship.thumbnailUrl) && Objects.equals(reservations, spaceship.reservations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, maxPassengers, maxSpeed, maxLoad, buildDate, notes, imageUrl, videoUrl, thumbnailUrl, reservations);
    }

    @Override
    public String toString() {
        return "Spaceship{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", maxPassengers=" + maxPassengers +
                ", maxSpeed=" + maxSpeed +
                ", maxLoad=" + maxLoad +
                ", buildDate=" + buildDate +
                ", notes='" + notes + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                ", reservations=" + reservations +
                '}';
    }
}