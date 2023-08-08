package com.example.demojpa1.spaceship.model;

import com.example.demojpa1.reservation.model.Reservation;
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
    private Integer maxPassengers;

    @Column(name = "MAXSPEED")
    private Integer maxSpeed;

    @Column(name = "MAXLOAD")
    private Integer maxLoad;

    @Column(name = "WEIGHT")
    private Long weight;

    @Column(name = "BUILD")
    private Date buildDate;

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
        this.weight = spaceship.weight;
        this.buildDate = spaceship.buildDate;
        this.notes = spaceship.notes;
        this.imageUrl = spaceship.imageUrl;
        this.videoUrl = spaceship.videoUrl;
        this.thumbnailUrl = spaceship.thumbnailUrl;
        this.reservations = spaceship.reservations;
    }

    public Spaceship(String name, int maxPassengers, int maxSpeed, int maxLoad, long weight, Date buildDate, String notes, String imageUrl, String videoUrl, String thumbnailUrl, List<Reservation> reservations)  {
        this(name, maxPassengers, maxSpeed, maxLoad, weight, buildDate, notes, imageUrl, videoUrl, thumbnailUrl);
        this.reservations = reservations;
    }
    public Spaceship(String name, int maxPassengers, int maxSpeed, int maxLoad, long weight, Date buildDate, String notes, String imageUrl, String videoUrl, String thumbnailUrl)  {
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

    public Spaceship(Long id, String name, int maxPassengers, int maxSpeed, int maxLoad, long weight, Date buildDate, String notes, String imageUrl, String videoUrl, String thumbnailUrl, List<Reservation> reservations) {
        this(name, maxPassengers, maxSpeed, maxLoad, weight, buildDate, notes, imageUrl, videoUrl, thumbnailUrl);
        this.id = id;
        this.reservations = reservations;
    }

    public Spaceship updateFrom(Spaceship spaceship, boolean partial) {
        if(!partial || spaceship.name!=null) {this.name = spaceship.name;}
        if(!partial || spaceship.buildDate!=null) {this.buildDate = spaceship.buildDate;}
        if(!partial || spaceship.maxLoad!=null) {this.maxLoad = spaceship.maxLoad;}
        if(!partial || spaceship.maxSpeed!=null) {this.maxSpeed = spaceship.maxSpeed;}
        if(!partial || spaceship.maxPassengers!=null) {this.maxPassengers = spaceship.maxPassengers;}
        if(!partial || spaceship.imageUrl!=null) {this.imageUrl = spaceship.imageUrl;}
        if(!partial || spaceship.videoUrl!=null) {this.videoUrl = spaceship.videoUrl;}
        if(!partial || spaceship.thumbnailUrl!=null) {this.thumbnailUrl = spaceship.thumbnailUrl;}
        return this;
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