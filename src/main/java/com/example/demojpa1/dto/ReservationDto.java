package com.example.demojpa1.dto;

import com.example.demojpa1.planet.model.Planet;
import com.example.demojpa1.spaceship.model.Spaceship;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Data
public class ReservationDto implements Serializable {
    private Long id;
    private Date startDate;
    private Date endDate;
    private PlanetDto planet;
    private SpaceshipDto spaceship;
    @JsonManagedReference
    private List<CustomerDto> customers;
}
