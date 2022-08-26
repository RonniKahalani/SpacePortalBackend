package com.example.demojpa1.dto;

import com.example.demojpa1.reservation.model.Reservation;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Data
public class SpaceshipDto implements Serializable {
    private Long id;
    private String name;
    private int maxPassengers;
    private int maxSpeed;
    private int maxLoad;
    private Date buildDate;
    private String notes;
    private String imageUrl;
    private String videoUrl;
    private String thumbnailUrl;
    @JsonBackReference
    private List<Reservation> reservations;
}
